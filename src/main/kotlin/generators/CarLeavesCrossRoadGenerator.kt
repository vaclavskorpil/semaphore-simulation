package generators

import base.SimulationEventGenerator
import data.CrossroadSimulationState
import simulation.CrossroadSimulationEvent

/**
 * Generates CarLeavesCrossRoad events based on current state
 * */
class CarLeavesCrossRoadGenerator :
    SimulationEventGenerator<CrossroadSimulationState, CrossroadSimulationEvent.CarLeavesCrossRoad> {

    override fun nextEvent(simulationState: CrossroadSimulationState): CrossroadSimulationEvent.CarLeavesCrossRoad {
        TODO()
    }

    override fun nextEventAt(simulationState: CrossroadSimulationState): Int? {
        return simulationState.crossroad.carsCrossing.minOfOrNull { it.value.timeToLave }
    }

}