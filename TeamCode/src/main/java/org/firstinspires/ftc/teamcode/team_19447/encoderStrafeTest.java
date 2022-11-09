package org.firstinspires.ftc.teamcode.team_19447;

public class encoderStrafeTest extends LinearOpMode{

    public void runOpMode(){
        /*
         * Example Autonomous
         * Moves forward, goes left and right, goes back to original position
         */
        forward(50);
        leftStrafe(50);
        rightStrafe(100);
        leftStrafe(50);
        backward(50);

        //Logs encoder positions to telemetry
        while (opModeIsActive()) {

            telemetry.addData("encoder-front-left: ", leftF.getCurrentPosition());
            telemetry.addData("encoder-rear-left: ", leftR.getCurrentPosition());
            telemetry.addData("encoder-front-right: ", rightF.getCurrentPosition());
            telemetry.addData("encoder-rear-right: ", rightR.getCurrentPosition());
            telemetry.update();
        }
    }

}
