package org.firstinspires.ftc.teamcode.team_19446;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaCurrentGame;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TfodCurrentGame;

import java.util.Timer;
import java.util.TimerTask;
import java.util.List;

@Autonomous
public class AutoCameraTest extends LinearOpMode {
    private DcMotor LF, RF, LB, RB, Arm, Intake, Carousel, Turret;
    //private Timer time = new Timer();
    private VuforiaCurrentGame vuforiaPowerPlay;
    private TfodCurrentGame tfodPowerPlay;
    private List<Recognition> recs;
    //static int ticks = 0, parking  = -1;
    /*
    class Event extends TimerTask {
        public void run() {
            recs = tfodPowerPlay.getRecognitions();
            if (recs.size()==0 && parking  == -1) {
                ticks++;
            } else {
                if (ticks>=0 && ticks <= 40) parking  = 0;
                else if (ticks > 40 && ticks <=80) parking  = 1;
                else if (ticks > 80 && ticks <= 120) parking  = 2;
            }
            time.cancel();
            telemetry.addData("List:", recs);
            telemetry.addData("ticks:",ticks);
            telemetry.update();
        }
    }
     */

    @Override
    public void runOpMode() {
        LF = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        RF = hardwareMap.get(DcMotor.class, "motorFrontRight");
        LB = hardwareMap.get(DcMotor.class, "motorBackLeft");
        RB = hardwareMap.get(DcMotor.class, "motorBackRight");
        Carousel = hardwareMap.get(DcMotor.class, "Carousel");
        Intake = hardwareMap.get(DcMotor.class, "poggy");
        Arm = hardwareMap.get(DcMotor.class, "arm");
        Turret = hardwareMap.get(DcMotor.class, "Turret");

        LB.setDirection(DcMotorSimple.Direction.REVERSE);
        LF.setDirection(DcMotorSimple.Direction.REVERSE);
        Arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Turret.setDirection(DcMotorSimple.Direction.REVERSE);

        // camera initialization
        vuforiaPowerPlay = new VuforiaCurrentGame();
        tfodPowerPlay = new TfodCurrentGame();
        // testing initialzation function that takes direction over a camera name + calibration file
        vuforiaPowerPlay.initialize(
                "", // vuforiaLicenseKey
                hardwareMap.get(WebcamName.class, "Webcam 1"), // Change this to the camera name, default "Webcam 1"
                "", // webcamCalibrationFilename
                true, // useExtendedTracking
                false, // enableCameraMonitoring
                VuforiaLocalizer.Parameters.CameraMonitorFeedback.NONE, // cameraMonitorFeedback
                0, // dx
                0, // dy
                0, // dz
                AxesOrder.XZY, // axesOrder
                90, // firstAngle
                90, // secondAngle
                0, // thirdAngle
                true); // useCompetitionFieldTargetLocations
        // Set min confidence threshold to 0.3
        tfodPowerPlay.initialize(vuforiaPowerPlay, (float) 0.3, true, true);
        // Initialize TFOD before waitForStart.
        // Init TFOD here so the object detection labels are visible
        // in the Camera Stream preview window on the Driver Station.
        tfodPowerPlay.activate();
        // Enable following block to zoom in on target.

        waitForStart();

        Arm.setPower(-0.5);
        sleep(500);
        Arm.setPower(0);

        Arm.setPower(0);

        // backward
        move(-1,-1,-1,-1,430);
        move(0,0,0,0,100);
        // spin carousel
        Carousel.setPower(-0.8/2);
        sleep(4000);
        Carousel.setPower(0);

        // left strafe
        move(-1, 1, 1, -1, 300);
        move(0, 0, 0, 0, 100);
        // forward
        move(1,1,1,1,2000);
        move(0,0,0, 0,100);

        // strafe correction (left turn)
        move(-1, 1, -1, 1, 15);
        move(0, 0, 0, 0, 100);

        //
        // default parking spot
        if (parkingDetection() == 0) {

        } else if (parkingDetection()  == 1) {

        } else if (parkingDetection() ==2) {

        } else if (parkingDetection()  == 3) {

        }

        telemetry.addData("parking ", parkingDetection() );
        telemetry.update();

        // push freight out
        Intake.setPower(1);
        Arm.setPower(0);
        sleep(1000);
        // stop intake & drop arm
        Intake.setPower(0);
        Arm.setPower(0.25);
        sleep(500);

        // strafe into wall and park in shipping hub (and move arm forwards)
        Arm.setPower(-0.5);
        sleep(600);
        Turret.setPower(0.5);
        move(1, -1, -1, 1, 1200);
        move(0, 0, 0, 0, 100);
        Turret.setPower(0.2);
        Arm.setPower(0.25);
        move(1, 1, 1, 1, 2500);
        move(0, 0, 0, 0, 100);
        Arm.setPower(0);
        Turret.setPower(0);
    }
        public int parkingDetection(){
            recs = tfodPowerPlay.getRecognitions();

            // Parking object detection
            int parkingSpot = 0;
            int leave = 0;

            while (recs.size()==0) {
                recs = tfodPowerPlay.getRecognitions();
                telemetry.addData("detections: ", recs);

                if (recs.size()>0) {
                    for (int i = 0; i < recs.size(); i++) {

                        String detectedLabel = recs.get(i).getLabel();

                        if (detectedLabel.equals("TFODTest 2")) {
                            parkingSpot = 1;

                        } else if (detectedLabel.equals("TFODTest 3")) {
                            parkingSpot = 2;

                        } else if (detectedLabel.equals("TFODTest 4")) {
                            parkingSpot = 3;

                        } else {
                            telemetry.addData("NO DETECTIONS", "");

                        }


                    }
                }
                telemetry.update();
            }

            return parkingSpot;
            //time.scheduleAtFixedRate(new Event(), 1, 100);
        }


    public void move(double LF, double RF, double LB, double RB, int sleepMS) {
        this.LF.setPower(LF);
        this.LB.setPower(LB);
        this.RF.setPower(RF);
        this.RB.setPower(RB);
        sleep(sleepMS);
    }

}
