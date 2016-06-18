package com.agoda.formula.assesments;

import com.agoda.formula.FinishingStats;
import com.agoda.formula.RacingTeam;

import java.util.List;

/**
 * Created by Tkachi on 6/18/2016.
 */
public class HandlerAssessment implements Assessment {

    private final float handlingDistance;

    public HandlerAssessment(float handlingDistance){
        this.handlingDistance = handlingDistance;
    }
    public void runAssessment(List<RacingTeam> racingTeams, List<FinishingStats> finishedTeams, float currentTime) {
        racingTeams.sort((o1, o2) -> Float.compare(o1.getCurrentPosition(), o2.getCurrentPosition()));
        //check first team in the list
        float currentTeamPosition = racingTeams.get(0).getCurrentPosition();
        if(locatedCloseEnoughToHandle(racingTeams, 0, 1)){
            racingTeams.get(0).useHandle();
        }

        //checking all teams in the middle
        for(int i=1;i<racingTeams.size()-1;i++){
            if(locatedCloseEnoughToHandle(racingTeams, i, i-1) || locatedCloseEnoughToHandle(racingTeams, i, i+1)){
                racingTeams.get(i).useHandle();
            }
        }

        //checking last team in the list
        if(locatedCloseEnoughToHandle(racingTeams, racingTeams.size()-1, racingTeams.size()-2)){
            racingTeams.get(racingTeams.size()-1).useHandle();
        }
    }

    private boolean locatedCloseEnoughToHandle(List<RacingTeam> racingTeams, int firstTeamIndex, int secondTeamIndex){
        return Math.abs(racingTeams.get(firstTeamIndex).getCurrentPosition()-racingTeams.get(secondTeamIndex).getCurrentPosition())<=handlingDistance;
    }
}
