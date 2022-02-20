import data.CrossroadSimulationState
import generators.CarArriveGenerator
import simulation.CrossroadSimulation

fun main() {
    val simulation = CrossroadSimulation(
        eventCreator = CrossroadSimulationEventCreator(
            carGenerator = CarArriveGenerator(),
            semaphoreGenerator = CarArriveGenerator(),
            carLeavesCrossRoadGenerator = CarArriveGenerator()
        ),
    )
    val simulationEnd = 10000
    simulation.startSimulation(CrossroadSimulationState(), simulationEnd)
}

