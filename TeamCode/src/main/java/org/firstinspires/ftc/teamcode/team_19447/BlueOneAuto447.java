// In reference to the classroom, BlueOne refers to the upper left corner Blue Zone
// This program is designed if the robot has encoders. Otherwise, will need to manually calculate it

package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
@Disabled
public class BlueOneAuto447 extends LinearOpMode {

    //Declare OpMode Members
    private DcMotor motorFL = null;
    private DcMotor motorRL = null;
    private DcMotor motorFB = null;
    private DcMotor motorRB = null;
    private ElapsedTime runtime = new ElapsedTime();

    /* Calculate the COUNTS_PER_INCH for the specific drive train.
       Go to motor vendor website to determine the motor's COUNTS_PER_MOTOR_REV
       For external drive gearing, set DRIVE_GEAR_REDUCTION as needed.
       For example, use a value of 2.0 for a 12-tooth spur gear driving a 24-tooth spur gear.
       This is gearing DOWN for less speed and more torque.
       For gearing UP, use a gear ratio less than 1.0. Note this will affect the direction of wheel rotation. */
    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // No External Gearing.
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);
    static final double DRIVE_SPEED = 0.6;
    static final double TURN_SPEED = 0.5;

    //NOTE: EVERYTHING ABOVE CAN BE COPIED FOR EVERY AUTO PROGRAM MADE
    /* If there are no encoders we could probably do this instead:
       NOTE: "static final" defines a constant value in Java
    static final double countsPerMotor = a number
    static final double driveGearReduction = a number (maybe not needed)
    static final double wheelDiameterInches = 3.937; (this is equivalent to 10 cm)
    static final double countsPerInch = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) / (WHEEL_DIAMETER_INCHES * Math.PI);

    This part determines the drive speed and turn speed to determine the "encoder drive" below:

    static final double driveSpeed = 0.6;
    static final double turnSpeed = 0.5;

     */

    @Override
    public void runOpMode() {
        //Drive system variables
        DcMotor motorFL = hardwareMap.get(DcMotor.class, "motorFrontLeft");
        DcMotor motorBL = hardwareMap.get(DcMotor.class, "motorBackLeft");
        DcMotor motorFR = hardwareMap.get(DcMotor.class, "motorFrontRight");
        DcMotor motorBR = hardwareMap.get(DcMotor.class, "motorBackRight");

        /* To drive forward, robot need the motor on right side to be reversed, because the axles point in opposite directions.
           When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
           Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips */
        motorFR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorBR.setDirection(DcMotorSimple.Direction.REVERSE);
        motorFL.setDirection(DcMotorSimple.Direction.FORWARD);
        motorBL.setDirection(DcMotorSimple.Direction.FORWARD);

        motorFR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motorBR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        motorFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motorBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //Send telemetry message to indicate encoder reset
        telemetry.addData("Starting at", "%7d :%7d",
                motorFR.getCurrentPosition(),
                motorBR.getCurrentPosition(),
                motorFR.getCurrentPosition(),
                motorBR.getCurrentPosition());
        telemetry.update();

        waitForStart();

        //for the "timeouts" we could probably just use the "sleep" command to make it less confusing
        //need to ask what unit of measurement is used

        encoderDrive(DRIVE_SPEED, 48, 48, 5.0);  // Step 1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(TURN_SPEED, 12, -12, 4.0);  // Step 2: Turn Right 12 Inches with 4 Sec timeout
        encoderDrive(DRIVE_SPEED, -24, -24, 4.0);  // Step 3: Reverse 24 Inches with 4 Sec timeout

        telemetry.addData("Path", "Complete");
        telemetry.update();
        sleep(1000);

    }

    private void encoderDrive(double speed, int leftInches, int rightInches, double timeoutS) {
        int newLeftTarget1;
        int newRightTarget1;
        int newLeftTarget2;
        int newRightTarget2;
        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget1 = motorFL.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget1 = motorRB.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newLeftTarget2 = motorFB.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightTarget2 = motorRL.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            motorFL.setTargetPosition(newLeftTarget1);
            motorFB.setTargetPosition(newLeftTarget2);
            motorRL.setTargetPosition(newRightTarget2);
            motorRB.setTargetPosition(newRightTarget1);

            // Turn On RUN_TO_POSITION
            motorFL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorFB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motorRB.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            motorFL.setPower(Math.abs(speed)); //Math.abs returns absolute value of number for speed
            motorFB.setPower(Math.abs(speed));
            motorRL.setPower(Math.abs(speed));
            motorRB.setPower(Math.abs(speed));

            /* keep looping while we are still active, and there is time left, and both motors are running.
             Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
             its target position, the motion will stop.  This is "safer" in the event that the robot will
             always end the motion as soon as possible.
             However, if you require that BOTH motors have finished their moves before the robot continues
             onto the next step, use (isBusy() || isBusy()) in the loop test. */
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (motorFL.isBusy() && motorFB.isBusy() && motorRL.isBusy() && motorRB.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Running to", " %7d :%7d", newLeftTarget1,
                        newRightTarget1, newLeftTarget2, newRightTarget2);
                telemetry.addData("Currently at", " at %7d :%7d",
                        motorFB.getCurrentPosition(), motorFL.getCurrentPosition(), motorRB.getCurrentPosition(),
                        motorRL.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            motorFB.setPower(0);
            motorFL.setPower(0);
            motorRB.setPower(0);
            motorRL.setPower(0);

            // Turn off RUN_TO_POSITION
            motorFB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorRB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            motorRL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);
        }


    }
}
