package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.hardware.DcMotor;

public class lift447 {
    DcMotor Lift1;
    DcMotor Lift2;
    public static double forwardTicks = 52.3;

    public lift447(double liftPower1, double liftPower2) {
        //Lift (Two lifts)

       /* public void (int liftPower1, int liftPower2) {
            Lift1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lift2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            Lift1.setTargetPosition(ticks);
        } */

        public void Lift1 ( double cm){
            double tick = cm * forwardTicks;
            int ticks = (int) tick;

        }


    }
}

