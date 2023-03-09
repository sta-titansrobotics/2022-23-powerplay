// FINALIZED DRIVE CONTROLLED, DO NOT TOUCH ANYTHING HERE //

package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;


@TeleOp
public class amazingRace447 extends LinearOpMode {

    ElapsedTime timer = new ElapsedTime();
    //Just in case, might need to add preemptive values to power1 and power2 -- sometimes the elapsed timer might be off.
    //If needed, preemptive values will be power1 = 0.5, power2 = 1
    double power1;
    double power2;

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

        waitForStart();

        if (isStopRequested())
            return;

        while (opModeIsActive()) {

            //Driving

         /*   double y = -gamepad1.left_stick_y; // Remember, this is reversed!

            //STRAFING VARIABLE
            double x = gamepad1.left_stick_x * 1.1; // Counteract imperfect strafing

            //THIS IS THE TURNING VARIABLE
            double rx = gamepad1.right_stick_x;

            double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            double frontLeftPower = (y + x + rx) / denominator;
            double backLeftPower = (y - x + rx) / denominator;
            double frontRightPower = (y - x - rx) / denominator;
            double backRightPower = (y + x - rx) / denominator;

            motorFL.setPower(frontLeftPower*0.5);
            motorBL.setPower(backLeftPower*0.5);
            motorFR.setPower(frontRightPower);
            motorBR.setPower(backRightPower);*/

            //convert to seconds so that we can take the modulo
            long elapsedSeconds = (int) timer.time(TimeUnit.SECONDS);

            //Every 15 seconds, randomize the power for each of the motors
            //Can adjust speed range to make this even harder if we want
            if ((elapsedSeconds) % 15 == 0) {
                double min = 0.01;
                double max = 0.5;
                power1 = Math.random() * (max - min) + min;
                power2 = Math.random() * (max - min) + min;
            }

            if (gamepad1.dpad_up) {
                motorFL.setPower(power1); //change this to infinity
                motorBL.setPower(power1);
            }
            
            if (gamepad2.dpad_up) {
                motorFR.setPower(power2);
                motorBR.setPower(power2);
            }

            if (gamepad1.dpad_down) {
                motorFL.setPower(-power1);
                motorBL.setPower(-power1);
            }
            if (gamepad2.dpad_down) {
                motorFR.setPower(-power2);
                motorBR.setPower(-power2);
            }

            if (gamepad1.dpad_left || gamepad1.dpad_right) {
                motorFL.setPower(0);
                motorBL.setPower(0);
            }
            if (gamepad2.dpad_left || gamepad2.dpad_left) {
                motorBR.setPower(0);
                motorFR.setPower(0);
            }

           /* if (gamepad1.dpad_left) {
                motorFL.setPower(-0.5);
                motorBL.setPower(0.5);
                motorFR.setPower(1);
                motorBR.setPower(-1);
            }

            if (gamepad1.dpad_right) {
                motorFL.setPower(0.5);
                motorBL.setPower(-0.5);
                motorFR.setPower(-1);
                motorBR.setPower(1);
            }*/

            while (timer.seconds() >= 300) {
                motorFL.setPower(0);
                motorBL.setPower(0);
                motorFR.setPower(0);
                motorBR.setPower(0);
            }

            //Telemetry
            telemetry.addData("LF Power:", motorFL.getPower());
            telemetry.addData("LB Power:", motorBL.getPower());
            telemetry.addData("RF Power:", motorFR.getPower());
            telemetry.addData("RB Power:", motorBR.getPower());
            telemetry.addData("Current Timer: ", timer.seconds());
            telemetry.addData("Current Timer (Integer Seconds): ", elapsedSeconds);
            telemetry.update();
        }
    }
}