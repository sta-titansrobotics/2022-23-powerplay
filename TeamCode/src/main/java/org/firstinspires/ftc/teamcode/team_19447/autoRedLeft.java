package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class autoRedLeft extends LinearOpMode {

    public static double forwardTicks = 52.3;
    public static double strafeTicks = 54.05;
    double default_power = 1;
    private DcMotor LiftLeft, LiftRight;
    private Servo Cam, verticalRack;

    @Override
    public void runOpMode() {

        //HARDWARE MAPPING
        DcMotor FrontLeft = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor RearLeft = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor FrontRight = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor RearRight = hardwareMap.get(DcMotor.class, "motorBackRight");
        LiftLeft = hardwareMap.get(DcMotor.class, "Lift 1");
        LiftRight = hardwareMap.get(DcMotor.class, "Lift 2");
        //Turret = hardwareMap.get(DcMotor.class, "");
        //LinearSlides = hardwareMap.get(DcMotor.class, "LinearSlides");
        verticalRack = hardwareMap.get(Servo.class, "verticalRack");
        Cam = hardwareMap.get(Servo.class, "Cam");

        //Reverse left side motors
        FrontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        RearLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        //Reverse one of the lifts
        LiftLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        autoClass447 robot = new autoClass447(forwardTicks, strafeTicks, FrontLeft, RearLeft, FrontRight, RearRight);
        robot.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        if (isStopRequested()) return;

        //movement - remember capitalization
        lowerCam();
        activateCam();
        pickupCone();
        robot.Forward(110, true);
        liftPosition(20, 1, 1, true);
        dropCone();
       /* robot.TurnLeft(30, 1, 1, true);
        liftPosition(38, 1, 1, true); //2000 ticks
        robot.Forward(15, true);
        dropCone();
        robot.Backward(15, true);
        robot.TurnRight(50, 1, 1, true);*/
        /*pickupCone();
        robot.Forward(20, true);
        robot.TurnLeft(40, 1, 1, true);
        dropCone();
        robot.Backward(20, true);
        robot.TurnRight(20, 1,1,true);
        robot.Forward(60, true);
        liftPosition(30, 1, 1, true);
        robot.Forward(3, true);
        dropCone();*/

        robot.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        while (opModeIsActive()) {

            telemetry.addData("encoder-front-left: ", FrontLeft.getCurrentPosition());
            telemetry.addData("encoder-rear-left: ", RearLeft.getCurrentPosition());
            telemetry.addData("encoder-front-right: ", FrontRight.getCurrentPosition());
            telemetry.addData("encoder-rear-right: ", RearRight.getCurrentPosition());
            telemetry.update();
        }
    }

       private void liftPosition(int distanceCM, double Speed, int Tolerance, boolean NextSequence){
            // convert encoder ticks to centimetres
            double tick = distanceCM * forwardTicks;
            int ticks = (int) tick;

            LiftLeft.setTargetPosition(ticks);
            LiftRight.setTargetPosition(ticks);
            LiftLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LiftRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            LiftLeft.setPower(Speed); //prob best to leave the speed as 1.
            LiftRight.setPower(Speed);
            ((DcMotorEx) LiftLeft).setTargetPositionTolerance(Tolerance);
            ((DcMotorEx) LiftRight).setTargetPositionTolerance(Tolerance);
            while (LiftLeft.isBusy() && LiftRight.isBusy() && NextSequence) {
            }
        }


   /* public void SlidesPosition(int Value, double Speed, int Tolerance, boolean NextSequence) {
        LinearSlides.setTargetPosition(Value);
        LinearSlides.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LinearSlides.setPower(Speed);
        ((DcMotorEx) LinearSlides).setTargetPositionTolerance(Tolerance);
        while (LinearSlides.isBusy() && NextSequence) {
        }
    }*/

       private void lowerCam() {
           double position = 0;
           while(position < 1) {
               position += 0.1;
               verticalRack.setPosition(position);
           }

       }
       private void activateCam() {
           sleep(800);
           Cam.setPosition(0.25); //activate camshaft
       }

       private void pickupCone() {
           verticalRack.setPosition(1); //raise back up after picking up the cone
       }

       private void dropCone() {
            verticalRack.setPosition(0); //lower position down
            Cam.setPosition(-0.25); //release camshaft
       }
    }




