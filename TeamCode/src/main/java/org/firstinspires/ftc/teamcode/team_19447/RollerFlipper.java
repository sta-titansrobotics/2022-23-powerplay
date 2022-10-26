package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class RollerFlipper extends LinearOpMode {
    DcMotor rollerFlipper = null;
    @Override
    public void runOpMode() {
        //initialize the motor
        rollerFlipper = hardwareMap.dcMotor.get("flipperMotor");
        //set motor direction
        rollerFlipper.setDirection(DcMotor.Direction.FORWARD);
        //use encoder or not?
        rollerFlipper.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //set power
        double flipperMotorPower;

        //wait for start button to be pushed
        waitForStart();

        while (opModeIsActive()){

            flipperMotorPower = gamepad1.touchpad_finger_1_y;
            flipperMotorPower = Range.clip(flipperMotorPower, -1, 1);
            rollerFlipper.setPower(flipperMotorPower);

        }

    }

}