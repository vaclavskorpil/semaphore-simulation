package generators

import base.SimulationEventCreator
import data.CrossroadSimulationState
import simulation.CrossroadSimulationEvent

/**
 * Generates Semaphore event in specified intervals
 * */
class SemaphoreChangeGenerator :
    SimulationEventCreator<CrossroadSimulationState, CrossroadSimulationEvent.SemaphoreChange> {

    override fun nextEvent(
        simulationState: CrossroadSimulationState,
        simulationEndTime: Int,
    ): CrossroadSimulationEvent.SemaphoreChange {
        TODO("Not yet implemented")
    }

    override fun nextEventAt(simulationState: CrossroadSimulationState, simulationEndTime: Int): Int? {
        TODO("Not yet implemented")
    }

    companion object {

        private const val t1 = 50 //semaphore south to north
        private const val t2 = 100 // semaphore2 east to west
    }
}
