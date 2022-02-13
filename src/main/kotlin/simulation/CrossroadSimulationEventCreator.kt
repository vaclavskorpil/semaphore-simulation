import base.SimulationEventGenerator
import data.CrossroadSimulationState
import generators.CarArriveGenerator
import generators.CarLeavesCrossRoadGenerator
import simulation.CrossroadSimulationEvent

/**
 * Generates crossroad simulation events. Would use generators for every simulation event.
 * Checks all times of generator.nextEventAt() and chose the closest one. Then use that generator to generate new event.
 * */
class CrossroadSimulationEventCreator : SimulationEventGenerator<CrossroadSimulationState, CrossroadSimulationEvent> {

    private val carGenerator = CarArriveGenerator()
    private val semaphoreGenerator = CarArriveGenerator()
    private val carLeavesCrossRoadGenerator = CarLeavesCrossRoadGenerator()

    override fun nextEvent(simulationState: CrossroadSimulationState): CrossroadSimulationEvent {
        TODO("Not yet implemented")
    }

    override fun nextEventAt(simulationState: CrossroadSimulationState): Int? {
        TODO("Not yet implemented")
    }
}
