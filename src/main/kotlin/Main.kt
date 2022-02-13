import data.CrossroadSimulationState
import simulation.CrossroadSimulation

fun main() {
    val simulation = CrossroadSimulation(
        simulationEnd = Int.MAX_VALUE,
        eventCreator = CrossroadSimulationEventCreator(),
        initialState = CrossroadSimulationState(),
    )

    simulation.startSimulation()
}