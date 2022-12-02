// FINALIZED DRIVE CONTROLLED, DO NOT TOUCH ANYTHING HERE //

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

    private DcMotor Lift1;
    private DcMotor Lift2;

    @Override
    public void runOpMode() {

        //Moving
        DcMotor motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");

        //Reverse left side motors
        motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBL.setDirection(DcMotorSimple.Direction.REVERSE);

        //pickup
        //DigitalChannel Pickup;
        //Pickup = hardwareMap.get(DigitalChannel.class, "Pickup");

        //Lift (Two lifts)
        DcMotor Lift1 = hardwareMap.get(DcMotor.class, "Lift 1");
        Lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor Lift2 = hardwareMap.get(DcMotor.class, "Lift 2");
        Lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Lift1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Lift2.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Roller Flipper
   //     DcMotor rollerFlipper = hardwareMap.get(DcMotor.class, "rollerFlipper");
     //   rollerFlipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //double flipperMotorPower;

        Servo verticalRack = hardwareMap.get(Servo.class, "verticalRack");
        double rackPosition = 0;

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

        //cam
        Servo cam = hardwareMap.get(Servo.class,"Cam");

        waitForStart();

        if (isStopRequested())
            return;

        while (opModeIsActive()) {

            //Driving

            double y = -gamepad1.left_stick_y; // Remember, this is reversed!

            //STRAFING VARIABLE
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing

            //THIS IS THE TURNING VARIABLE
            double rx = gamepad1.right_stick_x;

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
            Lift1.setPower(gamepad2.left_stick_y);
            Lift2.setPower(-gamepad2.left_stick_y);

            //vertical rack
            rackPosition = verticalRack.getPosition();
            rackPosition += 0.2 * gamepad2.right_stick_y;
            verticalRack.setPosition(rackPosition);

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
  /*      if (gamepad1.left_bumper) {
               rollerFlipper2.setPosition(-0.25);
                rollerFlipper.setPower(1);
           }
*/
           /* //Intake

            intakeMotorPower = gamepad1.touchpad_finger_1_y;
            intakeMotorPower = Range.clip(intakeMotorPower, -1, 1);
            Intake1.setPower(intakeMotorPower);
            Intake2.setPower(intakeMotorPower);*/


            //cam picker-upper (idk the name lmao its the thing that goes in the hole of the cone and picks it up)
            //changes on and off via clicking the same button. if the user clicks once, it will go up, if the user clicks twice, it will go down.
            if (gamepad2.left_bumper){
                cam.setPosition(-0.25); //set to 45 deg
            }
            if (gamepad2.right_bumper){
                cam.setPosition(0.25);
            }

            //PRESETS:
            //1cm = 52.4 ticks

            //Ground junction preset position.
            if (gamepad2.a){
                moveLift(1,500);
                verticalRack.setPosition(-1);
            }

            //Low junction preset position  original 1782 ticks
            if (gamepad2.x){  //34cm
                moveLift(1,1400);
                verticalRack.setPosition(1);
            }
            //Medium junction preset position
            if (gamepad2.b){ //59 cm
                moveLift(1,2500);
                verticalRack.setPosition(1);
            }
            //High junction preset position
            if (gamepad2.y) { //84 cm
                moveLift(1,3500);
                verticalRack.setPosition(1);
            }

            //lift presents (low, med, high) have to put actual button


            // Add sensors to telemetry???
            telemetry.addData("LF Power:", motorFL.getPower());
            telemetry.addData("LB Power:", motorBL.getPower());
            telemetry.addData("RF Power:", motorFR.getPower());
            telemetry.addData("RB Power:", motorBR.getPower());
            telemetry.addData("LF Position:", motorFL.getCurrentPosition());
            telemetry.addData("LB Position:", motorBL.getCurrentPosition());
            telemetry.addData("RF Position:", motorFR.getCurrentPosition());
            telemetry.addData("RB Position:", motorBR.getCurrentPosition());
            telemetry.addData("Lift Power:",Lift1.getPower());
            telemetry.addData("Lift Power:",Lift2.getPower());
            telemetry.addData("Lift Encoder Position: ", Lift1.getCurrentPosition());
            telemetry.addData("Lift Encoder Position: ", Lift2.getCurrentPosition());
            telemetry.addData("Vertical rack: ", verticalRack.getPosition());
            telemetry.addData("Cam", cam.getPosition());

            //  telemetry.addData("Roller Flipper Power:",rollerFlipper.getPower());
            //    telemetry.addData("Roller Flipper Encoder Position: ", rollerFlipper.getCurrentPosition());
            // telemetry.addData("Intake1 Power:",Intake1.getPower());
            //telemetry.addData("Intake2 Power:",Intake2.getPower());
            //telemetry.addData("Intake1 Encoder Position: ",Intake1.getCurrentPosition());
            //telemetry.addData("Intake2 Encoder Position: ", Intake2.getCurrentPosition());
            //telemetry.addData("Roller Flipper 2(Servo)", rollerFlipper2.getPosition());
            telemetry.update();
        }



        }

        //
        public void motorPower(double power) {
            Lift1.setPower(power);
            Lift2.setPower(power);
        }

        public void setLiftMode(DcMotor.RunMode mode){
           Lift1.setMode(mode);
           Lift2.setMode(mode);
    }

        public void moveLift(double power, int ticks){
            Lift1.setTargetPosition(ticks);
            Lift2.setTargetPosition(ticks);
            setLiftMode(DcMotor.RunMode.RUN_TO_POSITION);

            motorPower(power);
//
            while (Lift1.isBusy() && Lift2.isBusy()) {

                telemetry.addData("encoder-left-lift", Lift1.getCurrentPosition() + " busy= " + Lift1.isBusy());
                telemetry.addData("encoder-right-lift", Lift2.getCurrentPosition() + " busy= " + Lift2.isBusy());
                telemetry.update();
                }
            }

}