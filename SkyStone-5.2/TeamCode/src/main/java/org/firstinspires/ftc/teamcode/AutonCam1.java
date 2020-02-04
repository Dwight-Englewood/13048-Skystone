package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.Hardware.Bot;
import org.firstinspires.ftc.teamcode.Hardware.MovementEnum;
import org.firstinspires.ftc.teamcode.TensorFlow.Spot;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import java.util.List;


@Autonomous(name="blue cam", group="Autonomous")
public class AutonCam1 extends OpMode {
    private ElapsedTime runtime = new ElapsedTime();
    public TensorFlow gochu = new TensorFlow();


    //    private DigitalChannel DigChannel;
    Bot robot = new Bot();
    int auto = 0;

    public int left = 0;
    public int right = 0;
    public int middle = 0;
    public int notvis = 0;
    public int brick = 0;

    public void init() {
        robot.init(hardwareMap, telemetry, false);
        gochu.init(hardwareMap);

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
        gochu.start();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        try {
            switch (auto) {
                case 0:
                    robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    telemetry.addData(">", robot.FL.getCurrentPosition());
                    telemetry.addData(">", auto);
                    telemetry.update();


                    auto++;
                    break;


                case 1:
                    int en = robot.autonDrive(MovementEnum.FORWARD, 1200);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);
//                robot.drivePower(1.0);

                    if (en >= 1200) {
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;


                case 2:
                    robot.joint.setPosition(0.55);
                    Thread.sleep(1000);
                    runtime.reset();
                    auto++;


                case 3:

                    TensorFlow.Spot detect = TensorFlow.getSkystonePosBlue(telemetry);
                    switch (detect) {
                        case LEFT:
                            left++;
                            break;

                        case RIGHT:
                            right++;
                            break;

                        case MIDDLE:
                            middle++;
                            break;

                        case NOTVISIBLE:
                            notvis++;
                            break;

                    }
                    if (runtime.milliseconds() > 1500) {
                        if (left > right || left > middle) { //LEFT
                            brick = 1;
                        } else if (right > middle || right > left) { //RIGHT
                            brick = 2;
                        } else if (middle > left || middle > right) { //MIDDLE
                            brick = 3;
                        }
                    }
                    break;

                case 4:
                    if(brick == 1) {


                    }



                case 5:
                    if(brick == 1) {
                        en = robot.autonDrive(MovementEnum.LEFTSTRAFE, 400);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.drivePower(0.5);
//                      robot.drivePower(1.0);

                        if (en >= 400) {
                            robot.autonDrive(MovementEnum.STOP, 0);
                            robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                            robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                            robot.clamp.setPosition(1.0);
                            robot.strafePower(0.0);
                            Thread.sleep(1000);
                            runtime.reset();
                            auto++;
                        }
                        break;
                    }
                    else if(brick == 2) {
                        en = robot.autonDrive(MovementEnum.RIGHTSTRAFE, 400);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                        robot.drivePower(0.5);
//                      robot.drivePower(1.0);

                        if (en >= 400) {
                            robot.autonDrive(MovementEnum.STOP, 0);
                            robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                            robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                            robot.clamp.setPosition(1.0);
                            robot.strafePower(0.0);
                            Thread.sleep(1000);
                            runtime.reset();
                            auto++;
                        }
                        break;
                    }
                    else if(brick == 3) {
                        robot.clamp.setPosition(1.0);
                        Thread.sleep(1000);
                        runtime.reset();
                        auto++;

                    }

                case 6:
                    en = robot.autonDrive(MovementEnum.BACKWARD, 800);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);
//                robot.drivePower(1.0);



                    telemetry.update();


                    if (en >= 800) {
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;

                case 7:
                    en = robot.autonDrive(MovementEnum.LEFTTURN, 600);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);
//                robot.drivePower(1.0);

                    if (en >= 600) {
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
                    if (en >= 2000) {
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;

                case 9:
                    en = robot.autonDrive(MovementEnum.RIGHTTURN, 600);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);
//                robot.drivePower(1.0);

                    if (en >= 600) {
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        robot.hook.setPosition(1.0);
                        Thread.sleep(1000);
                        auto++;
                    }
                    break;

                case 10:
                    en = robot.autonDrive(MovementEnum.FORWARD, 200);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
                    robot.drivePower(0.5);
//                robot.drivePower(1.0);

                    if (en >= 200) {
                        robot.autonDrive(MovementEnum.STOP, 0);
                        robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                        robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                        robot.strafePower(0.0);
                        auto++;
                    }
                    break;


                case 11:
                    robot.lift.setTargetPosition(400);
                    robot.clamp.setPosition(0.15);
                    Thread.sleep(1000);
                    runtime.reset();
                    auto++;
                    break;


                case 12:




                }
            }
            catch(InterruptedException e){
            Thread.currentThread().interrupt();  // set interrupt flag
            System.out.println("Failed to compute sum");
        }
        telemetry.addData("FL: ", robot.FL.getCurrentPosition());
        telemetry.addData("FR: ", robot.FR.getCurrentPosition());
        telemetry.addData("BL: ", robot.BL.getCurrentPosition());
        telemetry.addData("BR: ", robot.BR.getCurrentPosition());
        telemetry.update();
    }
}






