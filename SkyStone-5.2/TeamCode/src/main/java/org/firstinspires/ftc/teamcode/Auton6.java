package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.DigitalChannel;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.teamcode.Hardware.MovementEnum;
//import org.firstinspires.ftc.teamcode.TensorFlowStuff.TensorFlow;
import org.firstinspires.ftc.teamcode.Hardware.Bot;
import java.lang.Thread;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
//import com.vuforia.CameraDevice;

@Autonomous(name="no foundation red", group="Autonomous")
public class Auton6 extends OpMode{
    private ElapsedTime runtime = new ElapsedTime();
    //    private DigitalChannel DigChannel;
    Bot robot = new Bot();
    int auto = 0;

    public void init() {
        robot.init(hardwareMap, telemetry, false);
//        tensorFlow.init(hardwareMap, telemetry);
        telemetry.addData("Status", "Initialized");
        telemetry.update();



//        robot.hook.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.FL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.BL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.BR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robot.FR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop(){
            switch (auto) {
                case 0:
                    robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    telemetry.addData(">", robot.FL.getCurrentPosition());
                    telemetry.addData(">", auto);
                    telemetry.update();


                    auto++;
                    break;


                case 1:
                    int en = robot.autonDrive(MovementEnum.FORWARD, 2000);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);
//                robot.drivePower(1.0);
                    telemetry.addData("Cas1, en: ", en);
                    telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                    telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                    telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                    telemetry.addData("BR: ", robot.BR.getCurrentPosition());


                    telemetry.update();


                    if(en >= 2500){
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;


                case 2:
                    en = robot.autonDrive(MovementEnum.LEFTSTRAFE, 2000);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);

                    telemetry.addData("Cas1, en: ", en);
                    telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                    telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                    telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                    telemetry.addData("BR: ", robot.BR.getCurrentPosition());


                    telemetry.update();
                    if (en >= 2000) {
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;


        }

    }
}