package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class Intake extends LinearOpMode {
    DcMotor Intake1 = null;
    DcMotor Intake2 = null;

    @Override
    public void runOpMode() {
        //initialize the motor
        Intake1 = hardwareMap.dcMotor.get("Intake1");
        //set motor direction
        Intake1.setDirection(DcMotor.Direction.FORWARD);
        //use encoder or not?
        Intake1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        Intake2 = hardwareMap.dcMotor.get("Intake1");
        //set motor direction
        Intake2.setDirection(DcMotor.Direction.FORWARD);
        //use encoder or not?
        Intake2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //set power
        double intakeMotorPower;

        //wait for start button to be pushed
        waitForStart();

        while (opModeIsActive()) {

            intakeMotorPower = gamepad1.touchpad_finger_1_y;
            intakeMotorPower = Range.clip(intakeMotorPower, -1, 1);
            Intake1.setPower(intakeMotorPower);
            Intake2.setPower(intakeMotorPower);

        }


    }

}