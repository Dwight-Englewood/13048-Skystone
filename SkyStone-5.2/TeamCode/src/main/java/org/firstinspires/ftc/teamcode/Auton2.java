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

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;
//import com.vuforia.CameraDevice;

@Autonomous(name="AutonFwRight", group="Autonomous")
public class Auton2 extends OpMode {
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
    public void loop() {
        switch (auto) {
            case 0:
                robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                telemetry.addData(">", robot.FL.getCurrentPosition());
                telemetry.addData(">", auto);
                telemetry.update();


                auto++;
                break;

            case 1:
                int en = robot.autonDrive(MovementEnum.FORWARD, 2500);
                robot.drivePower(0.5);
                robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
//                robot.drivePower(1.0);
                telemetry.addData("Cas1, en: ", en);

                telemetry.update();

                if(en >= 2500){
                    robot.autonDrive(MovementEnum.STOP, 0);
                    robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    auto++;
                }
                break;

            case 2:
                en = robot.autonDrive(MovementEnum.RIGHTSTRAFE, 2500);
                robot.strafePower(- 0.5);
                robot.changeRunModeAuton(DcMotor.RunMode.RUN_TO_POSITION);
//                robot.drivePower(1.0);
                telemetry.addData("Cas2, en: ", en);

                telemetry.update();

                if(en >= 2500){
                    robot.autonDrive(MovementEnum.STOP, 0);
                    robot.changeRunModeAuton(DcMotor.RunMode.RUN_USING_ENCODER);
                    auto++;
                }
                break;

            case 3:
                robot.changeRunModeAuton(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                auto++;
                break;

/*            case 4:
                robot.autonDriveUltimate(MovementEnum.BACKWARD, 140, 0.5);
                if (Math.abs(robot.FL.getCurrentPosition()) >= Math.abs(robot.FL.getTargetPosition())){
                    auto++;
                }
                break;

            case 5:
                BigThonk = (BigThonk != TensorFlow.TFState.NOTVISIBLE) ? BigThonk : tensorFlow.getState();
                if(BigThonk != TensorFlow.TFState.NOTVISIBLE){auto++;}
                else {
                    BigThonk = TensorFlow.TFState.RIGHT;
                    auto = 6;
                }
                break;

            case 6:
                CameraDevice.getInstance().setFlashTorchMode(false);
                tensorFlow.stop();
                robot.changeRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                auto++;
                break;

            case 7:
                robot.autonDriveUltimate(MovementEnum.RIGHTSTRAFE, 280, 0.2);
                if (Math.abs(robot.FL.getCurrentPosition()) >= Math.abs(robot.FL.getTargetPosition())){
                    auto++;
                }
                break;

            case 8:
                if(Math.abs(-80- robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle ) > 3) {
                    robot.adjustHeading(-80);
                }
                else if(Math.abs(-80 - robot.gyro.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle ) < 3) {
                    robot.drive(MovementEnum.STOP, 0);
                    auto++;
                }
                break;

            case 9:
                robot.changeRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                auto++;
                break;

            case 10:
                robot.autonDriveUltimate(MovementEnum.FORWARD, 280, 0.6);
                if (Math.abs(robot.FL.getCurrentPosition()) >= Math.abs(robot.FL.getTargetPosition())){
                    auto++;
                }
                break;*/

        }
        telemetry.update();
    }
}