import base.SimulationEventGenerator
import data.CrossroadSimulationState
import generators.CarArriveGenerator
import generators.CarLeavesCrossRoadGenerator
import simulation.CrossroadSimulationEvent

/**
 * Generates crossroad simulation events. Would youse generators for every simulation event.
 * Would always check all times of next events and chose the closest one. Then use that generator to generate new event
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
