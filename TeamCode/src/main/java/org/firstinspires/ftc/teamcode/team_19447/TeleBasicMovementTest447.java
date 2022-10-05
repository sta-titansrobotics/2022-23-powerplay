/*The purpose of this code is simply to test the basic movement of the robot when in tele
  Since the official driveControlled447 code has already been set up with other motors, the
  controller would not allow us to run the code since those motors could not be found/defined by
  the robot itself. As a result, if we would want to make the code run, we would need to comment out
  some of those motors. But since the official code is already set up, I don't really want to make
  any more changes to it that could possibly screw us up in the future. So, this code will be the
  "test" for tele basic movement.
 */


package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class TeleBasicMovementTest447 extends LinearOpMode {

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
