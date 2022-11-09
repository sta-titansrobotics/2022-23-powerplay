package org.firstinspires.ftc.teamcode.team_19447;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class lift447 extends LinearOpMode {
    DcMotor rollerFlipper = null;

    @Override
    public void runOpMode() {
        //Lift (Two lifts)
        DcMotor Lift1 = hardwareMap.get(DcMotor.class, "Lift 1");
        Lift1.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        DcMotor Lift2 = hardwareMap.get(DcMotor.class, "Lift 2");
        Lift2.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
}
