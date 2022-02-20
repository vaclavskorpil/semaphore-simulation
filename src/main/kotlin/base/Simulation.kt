package base

import data.CrossroadSimulationState
import data.SimulationResult

interface Simulation<State : SimulationState, Event : SimulationEvent> {

    /**
     * Creates simulation timeline. Starts with initial state that is included in the result.
     * End when the simulation reaches [simulationEnd] time.
     *
     *
     * @param initialState initial state of the simulation
     * @param simulationEnd time when simulation ends
     * @return result of the simulation including initial state
     * */
    fun startSimulation(initialState: CrossroadSimulationState, simulationEnd: Int): SimulationResult<State, Event>
}