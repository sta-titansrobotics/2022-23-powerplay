package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

        //Lift (Two lifts)
        DcMotor Lift1 = hardwareMap.get(DcMotor.class, "Lift 1");
        Lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor Lift2 = hardwareMap.get(DcMotor.class, "Lift 2");
        Lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Servo TurretServo = hardwareMap.get(Servo.class, "Upper Rack");
        TurretServo.setPosition(0);
        double turretServoPower;

        //Roller Flipper
        DcMotor rollerFlipper = hardwareMap.get(DcMotor.class, "rollerFlipper");
        rollerFlipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double flipperMotorPower;

        Servo rollerFlipper2 = hardwareMap.get(Servo.class, "rollerFlipper 2");
        rollerFlipper2.setPosition(0);
        double rollerFlipper2Power;
        int clickA = 0;

        if (clickA %2 == 1){ //clickA has to count up tho?? need to add clickA++??
            rollerFlipper2Power = -0.5; //servo 90 deg to the left
        }
        else{
            rollerFlipper2Power = 0.5; //servo 90 deg to the right
        }

        //Intake
        DcMotor Intake1 = hardwareMap.get(DcMotor.class, "Intake1");
        Intake1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor Intake2 = hardwareMap.get(DcMotor.class, "Intake2");
        Intake2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double intakeMotorPower;

        //Upper rack / pinion slide
        Servo upperRack = hardwareMap.get(Servo.class, "Upper Rack");
        upperRack.setPosition(0);
        double upperRackPower;
        DcMotor upperRackMotor = hardwareMap.get(DcMotor.class, "Rack Rotation Motor");
        upperRackMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double upperRackMotorPower;

        // Capper
        DcMotor Capper = hardwareMap.get(DcMotor.class, "Capper");
        Capper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double capperPower;

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

            //Lift (apparently two lifts?)
            double liftPower = gamepad1.right_stick_y;
            liftPower = Range.clip(liftPower, -1, 1);
            Lift1.setPower(liftPower);
            Lift2.setPower(liftPower);
            //testd saadsfsadaaaasd fdsa df sa
            //Turret

                    //Motor for turret
            DcMotor Lift = hardwareMap.get(DcMotor.class, "Turret");
            Lift.setDirection(DcMotor.Direction.FORWARD);
            Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            double turretPower;

                    //Servo for turret
            Servo leftClaw = hardwareMap.get(Servo.class, "leftClaw");

            turretPower = gamepad1.right_stick_x;
            turretPower = Range.clip(turretPower, -1, 1);
            TurretServo.setPosition(turretPower);

            //a lot of other stuff will be added too unfortunately

            //Roller Flipper


            flipperMotorPower = gamepad1.touchpad_finger_1_y;
            flipperMotorPower = Range.clip(flipperMotorPower, -1, 1);
            rollerFlipper.setPower(flipperMotorPower);

            //don't need | rollerFlipper2Power = gamepad1.right_stick_y;
            //don't need | rollerFlipper2Power = Range.clip(rollerFlipper2Power, -1, 1)
            if (gamepad1.a){
                rollerFlipper2.setPosition(rollerFlipper2Power);
                clickA +=1;
            }

            //Intake

            intakeMotorPower = gamepad1.touchpad_finger_1_y;
            intakeMotorPower = Range.clip(intakeMotorPower, -1, 1);
            Intake1.setPower(intakeMotorPower);
            Intake2.setPower(intakeMotorPower);

            // Upper rack & pinion slide
            // This one uses servos if i remember

            upperRackPower = gamepad1.touchpad_finger_2_y;
            upperRackPower = Range.clip(upperRackPower, -1, 1);
            upperRack.setPosition(upperRackPower);
            //motor component
            upperRackMotorPower = gamepad1.touchpad_finger_2_x;
            upperRackMotorPower = Range.clip(upperRackMotorPower, -1, 1);
            upperRackMotor.setPower(upperRackMotorPower);

            // Capper
            capperPower = gamepad1.left_trigger;
            capperPower = Range.clip(capperPower, -1, 1);
            Capper.setPower(capperPower);


            //scissor picker-upper (idk the name lmao its the thing that goes in the hole of the cone and picks it up)
            Servo scissorPicker = hardwareMap.get(Servo.class, "Scissor Picker");
            upperRack.setPosition(0);
            double scissorPickerPower;

            }

            telemetry.addData("LF Power:", motorFL.getPower());
            telemetry.addData("LB Power:", motorBL.getPower());
            telemetry.addData("RF Power:", motorFR.getPower());
            telemetry.addData("RB Power:", motorBR.getPower());
            telemetry.addData("Lift Power:",Lift1.getPower());
            telemetry.addData("Lift Power:",Lift2.getPower());
            telemetry.addData("Lift Encoder Position: ", Lift1.getCurrentPosition());
            telemetry.addData("Lift Encoder Position: ", Lift2.getCurrentPosition());
            telemetry.addData("Roller Flipper Power:",rollerFlipper.getPower());
            telemetry.addData("Roller Flipper Encoder Position: ", rollerFlipper.getCurrentPosition());
            telemetry.addData("Intake1 Power:",Intake1.getPower());
            telemetry.addData("Intake2 Power:",Intake2.getPower());
            telemetry.addData("Intake1 Encoder Position: ",Intake1.getCurrentPosition());
            telemetry.addData("Intake2 Encoder Position: ", Intake2.getCurrentPosition());
            telemetry.addData("Upper Rack Power:",upperRack.getPosition());
            telemetry.addData("Upper Rack Motor Power:",upperRackMotor.getPower());
            telemetry.addData("Upper Rack Motor Encoder Position: ",upperRackMotor.getCurrentPosition());
            telemetry.addData("Capper Power:",Capper.getPower());
            telemetry.addData("Capper Encoder Position: ", Capper.getCurrentPosition());
            telemetry.addData("Roller Flipper 2(Servo)", rollerFlipper2.getPosition());
            telemetry.update();


        }
    }
