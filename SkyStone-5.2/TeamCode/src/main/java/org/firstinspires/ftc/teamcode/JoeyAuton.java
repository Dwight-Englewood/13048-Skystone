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

@Autonomous(name="red far 1", group="Autonomous")
public class JoeyAuton extends OpMode{
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
        try{
            switch (auto) {
                case 0:
                    robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    telemetry.addData(">", robot.FL.getCurrentPosition());
                    telemetry.addData(">", auto);
                    telemetry.update();


                    auto++;
                    break;

                case 1:
                    robot.clamp.setPosition(0.1);
                    Thread.sleep(1000);

                    auto++;
                    break;


                case 2:
                    int en = robot.autonDrive(MovementEnum.FORWARD, 2300);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);
//                robot.drivePower(1.0);
                    telemetry.addData("Cas1, en: ", en);
                    telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                    telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                    telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                    telemetry.addData("BR: ", robot.BR.getCurrentPosition());


                    telemetry.update();


                    if(en >= 2300){
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;

                case 3:

                    en = robot.autonDrive(MovementEnum.RIGHTSTRAFE, 7000);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);
//                robot.drivePower(1.0);
                    telemetry.addData("Cas1, en: ", en);
                    telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                    telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                    telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                    telemetry.addData("BR: ", robot.BR.getCurrentPosition());


                    telemetry.update();


                    if(en >= 7000){
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;

                case 4:
                    en = robot.autonDrive(MovementEnum.FORWARD, 300);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);
//                robot.drivePower(1.0);
                    telemetry.addData("Cas1, en: ", en);
                    telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                    telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                    telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                    telemetry.addData("BR: ", robot.BR.getCurrentPosition());


                    telemetry.update();


                    if(en >= 300){
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;

                case 5:
                    en = robot.autonDrive(MovementEnum.RIGHTSTRAFE, 1200);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);

                    telemetry.addData("Cas1, en: ", en);
                    telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                    telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                    telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                    telemetry.addData("BR: ", robot.BR.getCurrentPosition());

                    telemetry.update();

                    if(en >= 1200){
                        robot.autonDrive(MovementEnum.RIGHTSTRAFE, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        robot.hook.setPosition(0.0);
                        Thread.sleep(1000);
                        auto++;
                    }
                    break;


                case 6:
                    en = robot.autonDrive(MovementEnum.BACKWARD, 3400);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.strafePower(-0.5);
//                robot.drivePower(1.0);
                    telemetry.addData("Cas1, en: ", en);
                    telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                    telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                    telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                    telemetry.addData("BR: ", robot.BR.getCurrentPosition());


                    telemetry.update();


                    if(en >= 3400){
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        robot.hook.setPosition(1.0);
                        Thread.sleep(1000);
                        auto++;
                    }
                    break;


                case 7:
                    en = robot.autonDrive(MovementEnum.LEFTSTRAFE, 2900);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.strafePower(-0.5);
//                robot.drivePower(1.0);
                    telemetry.addData("Cas1, en: ", en);
                    telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                    telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                    telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                    telemetry.addData("BR: ", robot.BR.getCurrentPosition());


                    telemetry.update();


                    if(en >= 2900){
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;

                case 8:
                    en = robot.autonDrive(MovementEnum.FORWARD, 2200);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);
//                robot.drivePower(1.0);
                    telemetry.addData("Cas1, en: ", en);
                    telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                    telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                    telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                    telemetry.addData("BR: ", robot.BR.getCurrentPosition());


                    telemetry.update();


                    if(en >= 2200){
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;

                case 9:
                    en = robot.autonDrive(MovementEnum.LEFTSTRAFE, 1500);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.strafePower(-0.5);
//                robot.drivePower(1.0);
                    telemetry.addData("Cas1, en: ", en);
                    telemetry.addData("FL: ", robot.FL.getCurrentPosition());
                    telemetry.addData("FR: ", robot.FR.getCurrentPosition());
                    telemetry.addData("BL: ", robot.BL.getCurrentPosition());
                    telemetry.addData("BR: ", robot.BR.getCurrentPosition());


                    telemetry.update();


                    if(en >= 1500){
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;


            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // set interrupt flag
            System.out.println("Failed to compute sum");
        }
        telemetry.update();
    }
}