package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class driveControlled447 extends LinearOpMode {

    @Override
    public void runOpMode() {

        //Moving
        DcMotor motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");

        //Reverse right side motors
        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

        //pickup
        //DigitalChannel Pickup;
        //Pickup = hardwareMap.get(DigitalChannel.class, "Pickup");

        //Lift (Two lifts)
        DcMotor Lift1 = hardwareMap.get(DcMotor.class, "Lift 1");
        Lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor Lift2 = hardwareMap.get(DcMotor.class, "Lift 2");
        Lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Roller Flipper
   //     DcMotor rollerFlipper = hardwareMap.get(DcMotor.class, "rollerFlipper");
     //   rollerFlipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double flipperMotorPower;

        Servo verticalRack = hardwareMap.get(Servo.class, "verticalRack");


        //Intake
        /*
        DcMotor Intake1 = hardwareMap.get(DcMotor.class, "Intake1");
        Intake1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor Intake2 = hardwareMap.get(DcMotor.class, "Intake2");
        Intake2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double intakeMotorPower;
        */

        //Lift touch sensors
        //"Unable to find a hardware device with name Touch1 and type DigitalChannel"//
       /* DigitalChannel Touch1;
        DigitalChannel Touch2;
        Touch1 = hardwareMap.get(DigitalChannel.class, "Touch1");
        Touch2 = hardwareMap.get(DigitalChannel.class, "Touch2");
        */


        /* // Capper
        DcMotor Capper = hardwareMap.get(DcMotor.class, "Capper");
        Capper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double capperPower; */

        //cam
        Servo cam = hardwareMap.get(Servo.class,"Cam");

        waitForStart();

        if (isStopRequested())
            return;


        while (opModeIsActive()) {

            //Driving

            double y = -gamepad1.left_stick_y; // Remember, this is reversed!

            //STRAFING VARIABLE
            double x = gamepad1.right_stick_x * 1.1; // Counteract imperfect strafing

            //THIS IS THE TURNING VARIABLE
            double rx = gamepad1.left_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            motorFL.setPower(frontLeftPower);
            motorBL.setPower(backLeftPower);
            motorFR.setPower(frontRightPower);
            motorBR.setPower(backRightPower);

            if (gamepad1.dpad_up) {
                motorFL.setPower(1); //change this to infinity
                motorBL.setPower(1);
                motorFR.setPower(1);
                motorBR.setPower(1);
            }

            if (gamepad1.dpad_down) {
                motorFL.setPower(-1);
                motorBL.setPower(-1);
                motorFR.setPower(-1);
                motorBR.setPower(-1);
            }

            if (gamepad1.dpad_left) {
                motorFL.setPower(-1);
                motorBL.setPower(1);
                motorFR.setPower(1);
                motorBR.setPower(-1);
            }

            if (gamepad1.dpad_right) {
                motorFL.setPower(1);
                motorBL.setPower(-1);
                motorFR.setPower(-1);
                motorBR.setPower(1);
            }

            //Change buttons later
            //y & x mean y-coordinates (up and down) and x-coordinates (left and right), respectively.
            //Manual Lift (apparently two lifts?)
            double liftPower = gamepad2.left_stick_y;
            if (gamepad2.left_stick_y > 0) {
                Lift1.setPower(liftPower);
                Lift2.setPower(liftPower);
            }
            if (gamepad2.left_stick_y < 0) {
                Lift1.setPower(liftPower);
                Lift2.setPower(liftPower);
            }

        /*    //Lift Presets
            if (gamepad2.dpad_down) {
                while ((Touch1.getState() == true) && (Touch2.getState() == true)) {
                    telemetry.addData("Touch Sensors", "Are Not Pressed");
                    Lift1.setPower(1);
                    Lift2.setPower(1);

                }
                while ((Touch1.getState() == false) && (Touch2.getState() == false)) {
                    telemetry.addData("Touch Sensors", "Are Now Pressed");
                    Lift1.setPower(0);
                    Lift2.setPower(0);
                }
            }
         */


            //a lot of other stuff will be added too unfortunately
            //vertical rack
            double verticalRackPower = gamepad2.left_stick_y;
            verticalRackPower = Range.clip(liftPower, -1, 1);
            verticalRack.setPosition(verticalRackPower);

            //Roller Flipper
         /*   flipperMotorPower = gamepad1.touchpad_finger_1_y;
            flipperMotorPower = Range.clip(flipperMotorPower, -1, 1);
            rollerFlipper.setPower(flipperMotorPower);
*/
            //don't need | rollerFlipper2Power = gamepad1.right_stick_y;
            //don't need | rollerFlipper2Power = Range.clip(rollerFlipper2Power, -1, 1)
/*            Servo rollerFlipper2 = hardwareMap.get(Servo.class, "rollerFlipper 2");
            rollerFlipper2.setPosition(0);
            double rollerFlipper2Power;
*/
 //           int clickB = 0;
            //Click b to do the roller flipper thing.
 //           if (gamepad1.b) {
            /*clickB += 1;
                if (clickB % 2 == 1) {
                   rollerFlipper2Power = -0.5; //servo 90 deg to the left
                } else {
                    rollerFlipper2Power = 0.5; //servo 90 deg to the right
                }
                rollerFlipper2.setPosition(rollerFlipper2Power);
            }
*/
            //alternatively do this?:
   /*         if (gamepad1.right_bumper) {
                rollerFlipper2.setPosition(0.25);
                rollerFlipper.setPower(1);
            }

    */
  //        if (gamepad1.left_bumper) {
    //            rollerFlipper2.setPosition(-0.25);
      //          rollerFlipper.setPower(1);
        //    }

           /* //Intake

            intakeMotorPower = gamepad1.touchpad_finger_1_y;
            intakeMotorPower = Range.clip(intakeMotorPower, -1, 1);
            Intake1.setPower(intakeMotorPower);
            Intake2.setPower(intakeMotorPower);*/

            /*// Capper
            capperPower = gamepad1.left_trigger;
            capperPower = Range.clip(capperPower, -1, 1);
            Capper.setPower(capperPower);*/


            //cam picker-upper (idk the name lmao its the thing that goes in the hole of the cone and picks it up)
            //changes on and off via clicking the same button. if the user clicks once, it will go up, if the user clicks twice, it will go down.
            if (gamepad2.left_bumper){
                cam.setPosition(-0.25);
            }
            else if (gamepad2.right_bumper){
                cam.setPosition(0.25);
            }
        }

            // Add sensors to telemetry???
            telemetry.addData("LF Power:", motorFL.getPower());
            telemetry.addData("LB Power:", motorBL.getPower());
            telemetry.addData("RF Power:", motorFR.getPower());
            telemetry.addData("RB Power:", motorBR.getPower());
            telemetry.addData("Lift Power:",Lift1.getPower());
            telemetry.addData("Lift Power:",Lift2.getPower());
            telemetry.addData("Lift Encoder Position: ", Lift1.getCurrentPosition());
            telemetry.addData("Lift Encoder Position: ", Lift2.getCurrentPosition());
          //  telemetry.addData("Roller Flipper Power:",rollerFlipper.getPower());
        //    telemetry.addData("Roller Flipper Encoder Position: ", rollerFlipper.getCurrentPosition());
            // telemetry.addData("Intake1 Power:",Intake1.getPower());
            //telemetry.addData("Intake2 Power:",Intake2.getPower());
            //telemetry.addData("Intake1 Encoder Position: ",Intake1.getCurrentPosition());
        //telemetry.addData("Intake2 Encoder Position: ", Intake2.getCurrentPosition());
           // telemetry.addData("Capper Power:",Capper.getPower());
           // telemetry.addData("Capper Encoder Position: ", Capper.getCurrentPosition());
            //telemetry.addData("Roller Flipper 2(Servo)", rollerFlipper2.getPosition());
            telemetry.update();

        }
    }