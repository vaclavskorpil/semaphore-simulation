package base

/**
 * Generates specified events, based on the current simulation state
 * */
interface SimulationEventCreator<State : SimulationState, Event : SimulationEvent> {

    /**
     * Get next simulation event
     * */
    fun nextEvent(simulationState: State, simulationEndTime: Int): Event

    /**
     * Get information when next simulation event will occur
     * */
    fun nextEventAt(simulationState: State, simulationEndTime: Int): Int?
}