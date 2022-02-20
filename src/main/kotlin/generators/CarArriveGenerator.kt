package generators

import base.SimulationEventCreator
import data.CrossroadSimulationState
import simulation.CrossroadSimulationEvent

class CarArriveGenerator :
    SimulationEventCreator<CrossroadSimulationState, CrossroadSimulationEvent.CarArriveOnSemaphore> {

    override fun nextEvent(
        simulationState: CrossroadSimulationState,
        simulationEndTime: Int,
    ): CrossroadSimulationEvent.CarArriveOnSemaphore {
        TODO("Not yet implemented")
    }

    override fun nextEventAt(simulationState: CrossroadSimulationState, simulationEndTime: Int): Int? {
        TODO("Not yet implemented")
    }

    companion object {

        const val c1 = 10 // car north
        const val c2 = 10 // car east
        const val c3 = 10 // car south
        const val c4 = 10 // car west

    }
}