package base

/**
 * Generates specified events, based on the current simulation state
 * */
interface SimulationEventGenerator<State : SimulationState, Event : SimulationEvent> {

    /**
     * Get next simulation event
     * */
    fun nextEvent(simulationState: State): Event

    /**
     * Get information when next simulation event will occur
     * */
    fun nextEventAt(simulationState: State): Int?
}