package base

import kotlinx.coroutines.flow.StateFlow

interface Simulation {

    /**
     * Time when the simulation ends.
     * */
    val simulationEnd: Int

    /**
     * Current state of the simulation. Could be observed from the client.
     * */
    val simulationState: StateFlow<SimulationState>

    /**
     * Starts a simulation with initial state
     * */
    fun startSimulation()
}