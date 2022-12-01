package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.ArrayList;

@TeleOp

public class AprilTagAutonomousInitDetectionExample extends LinearOpMode {

    //Introduce variables
    //Set motor variables
    DcMotor motorFL;
    DcMotor motorBL;
    DcMotor motorFR;
    DcMotor motorBR;
    DcMotor LiftLeft;
    DcMotor LiftRight;
    Servo cam;
    Servo verticalRack;

    //Initializing encoder positions
    int leftPos1;
    int leftPos2;
    int rightPos1;
    int rightPos2;

    OpenCvCamera camera;
    AprilTagDetectionPipeline aprilTagDetectionPipeline;

    static final double FEET_PER_METER = 3.28084;

    // Lens intrinsics
    // UNITS ARE PIXELS
    // NOTE: this calibration is for the C920 webcam at 800x448.
    // You will need to do your own calibration for other configurations!
    double fx = 578.272;
    double fy = 578.272;
    double cx = 402.145;
    double cy = 221.506;

    // UNITS ARE METERS
    double tagsize = 0.166;

    // Tag IDs 1,2,3 from the 36h11 family
    int LEFT = 1;
    int MIDDLE = 2;
    int RIGHT = 3;

    int Zone = 0;

    AprilTagDetection tagOfInterest = null;

    @Override
    public void runOpMode()
    {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        camera = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);

        camera.setPipeline(aprilTagDetectionPipeline);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800,448, OpenCvCameraRotation.UPSIDE_DOWN);
            }

            @Override
            public void onError(int errorCode)
            {

            }
        });

        telemetry.setMsTransmissionInterval(50);

        //Initialize motors
        motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");
        //Lifts
        LiftLeft = hardwareMap.get(DcMotor.class, "Lift 1");
        LiftRight = hardwareMap.get(DcMotor.class, "Lift 2");
        //Touch Sensors? - Remember that digital channels use odd numbers for some reason, not the even numbers.
        //verticalRack
        verticalRack = hardwareMap.get(Servo.class, "verticalRack");
        //cam
        cam = hardwareMap.get(Servo.class, "Cam");

        //set mode to stop and reset encoders -- resets encoders to the 0 position
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Reverse left side motors
        motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBL.setDirection(DcMotorSimple.Direction.REVERSE);
        //Reverse one of the lift motors
        LiftLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //Initialize the positions to zero, since the motor has not moved yet
        leftPos1 = 0;
        leftPos2 = 0;
        rightPos1 = 0;
        rightPos2 = 0;

        /*
         * The INIT-loop:
         * This REPLACES waitForStart!
         */
        while (!isStarted() && !isStopRequested()) {
            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();

            if(currentDetections.size() != 0) {
                boolean tagFound = false;

                for(AprilTagDetection tag : currentDetections) {
                    if(tag.id == LEFT || tag.id == MIDDLE || tag.id == RIGHT) {
                        tagOfInterest = tag;
                        tagFound = true;
                        break;
                    }
                }

                if(tagFound) {
                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
                    tagToTelemetry(tagOfInterest);
                }
                else {
                    telemetry.addLine("Don't see tag of interest :(");

                    if(tagOfInterest == null) {
                        telemetry.addLine("(The tag has never been seen)");
                    }
                    else {
                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                        tagToTelemetry(tagOfInterest);
                    }
                }
            } else {
                telemetry.addLine("Don't see tag of interest :(");

                if(tagOfInterest == null) {
                    telemetry.addLine("(The tag has never been seen)");
                }
                else {
                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
                    tagToTelemetry(tagOfInterest);
                }
            }

            telemetry.update();
            sleep(20);
        }

        /*
         * The START command just came in: now work off the latest snapshot acquired
         * during the init loop.
         */

        /* Update the telemetry */
        if(tagOfInterest != null) {
            telemetry.addLine("Tag snapshot:\n");
            tagToTelemetry(tagOfInterest);
            telemetry.update();
        }

        else {
            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
            telemetry.update();
        }

        // Actually do something useful
        if (tagOfInterest == null || tagOfInterest.id == LEFT){
            //SIGNAL ZONE 1 -- this is when the signal is on the left, Zone 1
            Zone = 1;

            raiseRack();
            drive(110, 110, 110, 110, 1); //110 cm to the ground junction
            drive(-20,-20, 20,20, 1);
            dropCone();

        }
        else if (tagOfInterest.id == MIDDLE){
            //SIGNAL ZONE 2 -- this is when the signal is on the middle, Zone 2
            Zone = 2;

            raiseRack();
            drive(110, 110, 110, 110, 1); //110 cm to the ground junction
            drive(-20,-20, 20,20, 1);
            dropCone();

        }
        else {
            //SIGNAL ZONE 3 -- this is when the signal is on the right, Zone 3
            Zone = 3;

            raiseRack();
            drive(110, 110, 110, 110, 1); //110 cm to the ground junction
            drive(-20,-20, 20,20, 1);
            dropCone();

        }

        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
        while (opModeIsActive()) {
            sleep(20);
        }
    }

    //FUNCTIONS:
    public void moveLift(double power, double endPower, int ticks) {
        LiftLeft.setTargetPosition(ticks);
        LiftRight.setTargetPosition(ticks);

        LiftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LiftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motorPower(power);

        while(LiftLeft.isBusy()) {
            // wait for lift to reach desired height
        }

        motorPower(0);

        LiftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        LiftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        motorPower(endPower);
    }

    /**
     * Set power of both lift motors
     * @param power setPower
     */
    public void motorPower(double power) {
        LiftLeft.setPower(power);
        LiftRight.setPower(power);
    }

    /**
     * Change mode of cascading lift
     * @param mode setMode
     */
    public void setLiftMode(DcMotor.RunMode mode) {
        LiftLeft.setMode(mode);
        LiftRight.setMode(mode);
    }

    public void raiseRack() {
        verticalRack.setPosition(0);
    }

    public void pickupCone() {
        verticalRack.setPosition(1);
        sleep(2000);
        cam.setPosition(0.25); //turn 45 deg to pick it up
        sleep(1000);
        verticalRack.setPosition(0);
        sleep(1000);
    }

    public void dropCone() {
        cam.setPosition(-0.25); //revert and turn back the 45 deg to drop it.
    }

    public void rack(double position) {
        verticalRack.setPosition(position);
    }

    //will use a function that will take the distance and speed of the motors

    public void drive(int leftTarget1, int leftTarget2, int rightTarget1, int rightTarget2, double speed) {

        double forwardTicks = 52.3;
        double strafeTicks = 54.05;

        int ticks1 = (int) forwardTicks;
        int ticks2 = (int) strafeTicks;


        leftPos1 += leftTarget1;
        leftPos2 += leftTarget2; //This will change where the motor needs to be by the specific inputted amount
        rightPos1 += rightTarget1;
        rightPos2 += rightTarget2;

        // Using setTargetPosition and RUN_TO_POSITION, it forces motors to continue running until the encoders reach the specified target position
        // Multiplying by forward/strafe ticks in relation to forward/strafe based on what the robot is doing.
        if ((leftPos1 >= 0) && (leftPos2 >= 0) && (rightPos1 >= 0) && (rightPos2 >= 0)) { //Forward
            motorFL.setTargetPosition((leftPos1) * ticks1);
            motorBL.setTargetPosition((leftPos2) * ticks1);
            motorFR.setTargetPosition((rightPos1) * ticks1);
            motorBR.setTargetPosition((rightPos2) * ticks1);

        } else if ((leftPos1 < 0) && (leftPos2 < 0) && (rightPos1 < 0) && (rightPos2 < 0)) { //Backwards
            motorFL.setTargetPosition((leftPos1) * ticks1);
            motorBL.setTargetPosition((leftPos2) * ticks1);
            motorFR.setTargetPosition((rightPos1) * ticks1);
            motorBR.setTargetPosition((rightPos2) * ticks1);

        } else if (((leftPos1 < 0) && (rightPos2 < 0) && (leftPos2 >= 0) && (rightPos1 >= 0)) || (((leftPos2 < 0) && (rightPos1 < 0)) && (leftPos1 >= 0) && (rightPos2 >= 0))) {
            // Strafing left or right
            motorFL.setTargetPosition((leftPos1) * ticks2);
            motorBL.setTargetPosition((leftPos2) * ticks2);
            motorFR.setTargetPosition((rightPos1) * ticks2);
            motorBR.setTargetPosition((rightPos2) * ticks2);

        } else if (((leftPos2 > 0) && (rightPos1 > 0)) || ((leftPos2 == 0) && (rightPos1 == 0))) {
            //Strafing diagonally forward right or left, respectively.
            motorFL.setTargetPosition((leftPos1) * ticks2);
            motorBL.setTargetPosition((leftPos2) * ticks2);
            motorFR.setTargetPosition((rightPos1) * ticks2);
            motorBR.setTargetPosition((rightPos2) * ticks2);

        } else if (((leftPos2 < 0) && (rightPos1 < 0)) || ((leftPos1 < 0) && (rightPos2 < 0))) {
            //Strafing diagonally backward right or left, respectively.
            motorFL.setTargetPosition((leftPos1) * ticks2);
            motorBL.setTargetPosition((leftPos2) * ticks2);
            motorFR.setTargetPosition((rightPos1) * ticks2);
            motorBR.setTargetPosition((rightPos2) * ticks2);

        } else if (((leftPos1 < 0) && (rightPos2 > 0) && (leftPos2 < 0) && (rightPos1 > 0)) || (((leftPos2 > 0) && (rightPos1 < 0)) && (leftPos1 > 0) && (rightPos2 < 0))) {
            //Turning left or right, respectively.
            motorFL.setTargetPosition((leftPos1) * ticks2);
            motorBL.setTargetPosition((leftPos2) * ticks2);
            motorFR.setTargetPosition((rightPos1) * ticks2);
            motorBR.setTargetPosition((rightPos2) * ticks2);

        }

        motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorFR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motorBR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Encoders do not change speed automatically. Need to adjust speed ourselves
        motorFL.setPower(speed);
        motorBL.setPower(speed);
        motorFR.setPower(speed);
        motorBR.setPower(speed);

        //will stop automatically but need to prevent any other code from conflicting
        while (opModeIsActive() && motorFL.isBusy() && motorBL.isBusy() && motorFR.isBusy() && motorBR.isBusy()) {
        }

        //Stop driving so that it can perform the next command.
        motorFL.setPower(0);
        motorBL.setPower(0);
        motorFR.setPower(0);
        motorBR.setPower(0);

    }

    void tagToTelemetry(AprilTagDetection detection) {
        telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
    }
}