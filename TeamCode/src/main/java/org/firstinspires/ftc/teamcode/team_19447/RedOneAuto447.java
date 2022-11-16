package org.firstinspires.ftc.teamcode.team_19447;
//Used if our bot is on the top red side of the arena

import static android.os.SystemClock.sleep;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class RedOneAuto447 extends LinearOpMode {

    //Set motor variables
    private DcMotor motorFL;
    private DcMotor motorBL;
    private DcMotor motorFR;
    private DcMotor motorBR;
    private DcMotor Lift1;
    private DcMotor Lift2;
    private DcMotor Turret;
    private Servo Pinion;
    private Servo verticalRack;
    private Servo cam;

    //Initializing encoder positions
    private int leftPos1;
    private int leftPos2;
    private int rightPos1;
    private int rightPos2;

    //Touch Sensors?
    DigitalChannel Touch1;
    DigitalChannel Touch2;
    DigitalChannel Pickup;

    public void runOpMode() {

        //Initialize motors
        motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
        motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");
        //Lift
        Lift1 = hardwareMap.get(DcMotor.class, "Lift1");
        Lift2 = hardwareMap.get(DcMotor.class, "Lift2");
        //Touch Sensors?
        Touch1 = hardwareMap.get(DigitalChannel.class, "Touch1");
        Touch2 = hardwareMap.get(DigitalChannel.class, "Touch2");
        Pickup = hardwareMap.get(DigitalChannel.class, "Pickup");
        // set the digital channel to input.
        Touch1.setMode(DigitalChannel.Mode.INPUT);
        Touch2.setMode(DigitalChannel.Mode.INPUT);
        //Turret
        Turret = hardwareMap.get(DcMotor.class, "Turret");
        //pinion
        Pinion = hardwareMap.get(Servo.class, "Pinion");
        //rack
        verticalRack = hardwareMap.get(Servo.class, "verticalRack");
        //cam
        cam = hardwareMap.get(Servo.class,"Cam");

        //set mode to stop and reset encoders -- resets encoders to the 0 position
        motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        //Reverse left side motors
        motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBL.setDirection(DcMotorSimple.Direction.REVERSE);

        //Initialize the positions to zero, since the motor has not moved yet
        leftPos1 = 0;
        leftPos2 = 0;
        rightPos1 = 0;
        rightPos2 = 0;

        //any code after this command will not be executed until the match has started
        waitForStart();

        //can now set drive distance because of the function below; now we just need to input the distance
        //can also control the direction using the mecanum drivetrain directions here: https://gm0.org/en/latest/docs/software/tutorials/mecanum-drive.html

    //Building the circuit:

      //The ground junction:
        drive(-20, -20, 20, 20, 1); //turn left ~ 45 degrees
        drive(40, 40, 40, 40, 1);
        drive(-20,-20,-20,-20,1);

      //The second pole:
        drive(10, 10, -10, -10, 1); //revert to a straight-forward position
        drive(40, 40, 40, 40, 1);
        //do something with the lift here
        lift(1,1);
        sleep(100); //raise the lift for a given number of milliseconds - this we can just trial and error

        while (opModeIsActive()) {
            telemetry.addData("motorFL Encoder Position: ",motorFL.getCurrentPosition());
            telemetry.addData("motorBL Encoder Position: ",motorBL.getCurrentPosition());
            telemetry.addData("motorFR Encoder Position: ",motorFR.getCurrentPosition());
            telemetry.addData("motorBR Encoder Position: ",motorBR.getCurrentPosition());
            telemetry.update();
        }
      //The third pole

    }

    public void turret(double turretPower, long seconds) {
        Turret.setPower(turretPower);
        sleep(seconds * 1000);
    }

    public void lift(double liftPower, long seconds){
        Lift1.setPower(liftPower);
        Lift2.setPower(liftPower);
        sleep(seconds*1000);
        if ((Touch1.getState() == false) && (Touch2.getState() == false)) {
            telemetry.addData("Touch Sensors", "Are Pressed");
            Lift1.setPower(0);
            Lift2.setPower(0);
        }
        telemetry.update();
    }

    public void pinion(double position, long seconds){
        Pinion.setPosition(position);
        sleep(seconds*1000);
    }

    public void verticalRack(double position, long seconds){
        verticalRack.setPosition(position);
        sleep(seconds*1000);
    }

    public void pickupCone() {
        if (Pickup.getState() == false) {
            telemetry.addData("Pickup sensor", "is pressed");
            cam.setPosition(0.25); //turn 45 deg to pick it up
        }
    }

    public void dropCone() {
        cam.setPosition(-0.25); //revert and turn back the 45 deg to drop it.
    }



    //will use a function that will take the distance and speed of the motors based on the

    public void drive(int leftTarget1, int leftTarget2, int rightTarget1, int rightTarget2, double speed) {

        double forwardTicks = 52.3;
        double strafeTicks = 54.05;

        int ticks1 = (int) forwardTicks;
        int ticks2 = (int) strafeTicks;


        leftPos1 += leftTarget1; //By adding the "+=", it makes it equivalent to leftPos1 = leftPos1 + leftTarget1, therefore it will allow adding values to the position based on what the target is.
        leftPos2 += leftTarget2; //This will therefore change where the motor needs to be by the specific inputted amount
        rightPos1 += rightTarget1;
        rightPos2 += rightTarget2;

        // Using setTargetPosition and RUN_TO_POSITION, it forces motors to continue running until the encoders reach the specified target position
        // We are multiplying by forward/strafe ticks in relation to forward/strafe based on what the robot is doing.
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

        } else if (((leftPos2 > 0) && (rightPos1 > 0)) || ((leftPos2 == 0) && (rightPos1 == 0))){
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
        while(opModeIsActive() && motorFL.isBusy() && motorBL.isBusy() && motorFR.isBusy() && motorBR.isBusy()) {

        }

        //Stop driving so that it can perform the next command.
        motorFL.setPower(0);
        motorBL.setPower(0);
        motorFR.setPower(0);
        motorBR.setPower(0);

    }

}

