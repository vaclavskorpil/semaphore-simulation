package generators

import base.SimulationEventGenerator
import data.CrossroadSimulationState
import simulation.CrossroadSimulationEvent

class CarArriveGenerator : SimulationEventGenerator<CrossroadSimulationState, CrossroadSimulationEvent.CarArriveOnSemaphore> {

    override fun nextEvent(simulationState: CrossroadSimulationState): CrossroadSimulationEvent.CarArriveOnSemaphore {
        TODO("Not yet implemented")
    }

    override fun nextEventAt(simulationState: CrossroadSimulationState): Int? {
        TODO("Not yet implemented")
    }

    companion object {

        const val c1 = 10 // car north
        const val c2 = 10 // car east
        const val c3 = 10 // car south
        const val c4 = 10 // car west

    }
}