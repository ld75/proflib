package com.proflib.rdv.pomodoro;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

public class PomodoroTest {
@Test
@Disabled
public void runPomodoro()
{
        while(true)
        {
            for (int nbCycle = 0; nbCycle <=4; nbCycle++)
            {
                workInMinute(25);
                if (nbCycle==4) break;
                pauseInMinute(5);
            }
            pauseInMinute(20);
        }
}
    private void workInMinute(int minutes) {
        chronoPour("bosse",minutes);
    }
    private void pauseInMinute(int minutes) {
        chronoPour("pause",minutes);
    }
    private void chronoPour(String tache, int minutes) {
        System.out.println(tache+" pendant "+minutes+ " minutes");
    }
}
