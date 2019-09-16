package org.firstinspires.ftc.teamcode.TeleBop;/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardware.bot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

import com.qualcomm.hardware.rev.RevBlinkinLedDriver;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleBop",group="Teleop")
//@Disabled
public class TeleOp extends OpMode {
    // Declare OpMode members.
    private ElapsedTime timer = new ElapsedTime();
    bot robot = new bot();
    boolean Command;
    boolean wabbo = false;

    @Override
    public void init() {
        robot.init(hardwareMap, telemetry, false);
//        robot.resetServo();
        telemetry.addData("Status", "Initialized");
        robot.lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        telemetry.addData("Hook Power", robot.hook.getPower());
        telemetry.addData("Claw Position", robot.claw.getPosition());
        }

    @Override
    public void init_loop() {
    }

    @Override
    public void start() {
        timer.reset();
        robot.claw.setPosition(0.8);

        robot.blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.BREATH_BLUE);
    }

    /*
     * Code to run REPEATEDLY after the r hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
//        double leftPower = Range.clip(gamepad1.left_stick_y, -0.75,0.75);
//        double rightPower = Range.clip(gamepad1.right_stick_y, -0.75, 0.75);
//        robot.tankDriveNoStrafe(gamepad1.left_stick_y, gamepad1.right_stick_y);
        //TODO: After competition, comment out tankDriveNoStrafe and enable normal tankDrive for strafable Mechanum Wheels.
        if(gamepad1.x){wabbo = true;
            robot.blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.BLUE_VIOLET);
        }
        else if (gamepad1.y){wabbo = false;}
        robot.tankDrive(gamepad1.left_stick_y, gamepad1.right_stick_y,   gamepad1.left_trigger,gamepad1.right_trigger,wabbo, false);

        if (robot.hookLimit.getState()) {
            robot.hook.setPower(gamepad2.right_trigger * 0.75);

        } else if (!robot.hookLimit.getState()) {
            robot.hook.setPower(-(gamepad2.left_trigger * 0.75));

        }
            if (gamepad1.a) {
                robot.hook.setPower(1);
                robot.blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.DARK_GREEN);

            } else if (gamepad1.b) {
                robot.hook.setPower(-1);
                robot.blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.LIGHT_CHASE_RED);

            } else {
                robot.hook.setPower(0);
            }


/*
        Move = robot.liftLimit.getState();
        if (Move){
            robot.lift.setPower(gamepad2.right_stick_y * 0.75);
        }
        else if(!Move){
            robot.lift.setPower(Math.abs(gamepad2.right_stick_y * 0.75));
        }
        if (gamepad2.dpad_up){
            Move = true;
        }
        if (gamepad2.dpad_down) {
            Move2 = true;
        }
*/

        if(robot.liftLimit.getState()){
            robot.lift.setPower(gamepad2.right_stick_y * 0.75);

        }
        else if(!robot.liftLimit.getState() && gamepad2.right_stick_y > 0){
            robot.lift.setPower(gamepad2.right_stick_y * 0.75);
        }
        else{
            robot.lift.setPower(0);
        }

        //intake
        if(gamepad2.left_stick_y > 0.3) {
            robot.intake.setPower(gamepad2.left_stick_y * -1);
        }

        else if(gamepad2.left_stick_y < -0.3) {
            robot.intake.setPower(gamepad2.left_stick_y * -1);

        } else {
            robot.intake.setPower(0);
        }

        //claw
        if (gamepad2.b) {
            robot.claw.setPosition(0.10);
          //  robot.dumpEntry.setPosition(0.0);

        } else if (gamepad2.a) {
            robot.claw.setPosition(0.7);
           // Kevin is a furry robot.dumpEntry.setPosition(0.75);
        }

        if (gamepad2.y) {
            robot.dump.setPosition(0.75);
            robot.blinkin.setPattern(RevBlinkinLedDriver.BlinkinPattern.WHITE);

        } else{
            //if (gamepad2.x) {
            robot.dump.setPosition(0);
        }

        if (robot.intake.getPower() != 0){
            robot.dumpEntry.setPosition(0.0);}
        else {robot.dumpEntry.setPosition(0.75);}

//        telemetry.addData("power", robot.FL.getPower());
    }


    /*
     * Code to run ONCE after the driver hits STOP
     */


    @Override
    public void stop() {
    }

}