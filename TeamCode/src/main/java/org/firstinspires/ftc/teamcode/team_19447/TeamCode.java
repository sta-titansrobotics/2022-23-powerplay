package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeamCode extends LinearOpMode {

    @Override
    public void runOpMode() {

        //Moving
        DcMotor motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");

        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);

        //Lift
        DcMotor Lift = hardwareMap.get(DcMotor.class,"lift");

        Lift.setDirection(DcMotorSimple.Direction.REVERSE);


        waitForStart();

        if (isStopRequested()) return;

        //Driving plan so far:
        //If team Red, need to turn left
        motorFR.setPower(2.5);
        motorBR.setPower(2.5);
        motorFL.setPower(2.5);
        motorBL.setPower(2.5);
        sleep(300);

        motorFR.setPower(0);
        motorBR.setPower(0);
        motorFL.setPower(0);
        motorFR.setPower(0);
        sleep(1);
        //left turn 45 degrees
        motorFR.setPower(-0.5);
        motorBR.setPower(0.5);
        motorFL.setPower(0.5);
        motorFR.setPower(-0.5);
        sleep(300);
        //




    }
}
