import base.SimulationEventCreator
import data.CrossroadSimulationState
import generators.CarArriveGenerator
import simulation.CrossroadSimulationEvent

/**
 * Generates crossroad simulation events. Would use generators for every simulation event.
 * Checks all times of generator.nextEventAt() and chose the closest one. Then use that generator to generate new event.
 * */
class CrossroadSimulationEventCreator(
    private val carGenerator: CarArriveGenerator,
    private val semaphoreGenerator: CarArriveGenerator,
    private val carLeavesCrossRoadGenerator: CarArriveGenerator,
) : SimulationEventCreator<CrossroadSimulationState, CrossroadSimulationEvent> {

    override fun nextEvent(
        simulationState: CrossroadSimulationState,
        simulationEndTime: Int,
    ): CrossroadSimulationEvent {
        TODO("Not yet implemented")
    }

    override fun nextEventAt(simulationState: CrossroadSimulationState, simulationEndTime: Int): Int? {
        TODO("Not yet implemented")
    }
}
