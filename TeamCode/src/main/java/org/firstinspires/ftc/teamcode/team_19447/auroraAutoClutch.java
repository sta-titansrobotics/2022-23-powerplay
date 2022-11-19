package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

    @Autonomous
    public class auroraAutoClutch extends LinearOpMode {

        //Set motor variables
        private DcMotor motorFL;
        private DcMotor motorBL;
        private DcMotor motorFR;
        private DcMotor motorBR;

        public void runOpMode() {

            //Initialize motors
            motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
            motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
            motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
            motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");

            //set mode to stop and reset encoders -- resets encoders to the 0 position
            motorFL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorBL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            //Reverse left side motors
            motorFL.setDirection(DcMotorSimple.Direction.REVERSE);
            motorBL.setDirection(DcMotorSimple.Direction.REVERSE);

            waitForStart();

            if (isStopRequested())
                return;

            motorFL.setPower(1);
            motorFR.setPower(1);
            motorBL.setPower(1);
            motorBR.setPower(1);
            sleep(300);
            motorFL.setPower(0);
            motorFR.setPower(0);
            motorBL.setPower(0);
            motorBR.setPower(0);
            sleep(20000000);

        }
    }

