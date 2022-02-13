package data

import base.SimulationState
import simulation.CrossroadSimulationEvent
/**
 * State of the crossroad simulation.
 * */
data class CrossroadSimulationState(
    override val currentTime: Int = 0,
    override val currentEvent: CrossroadSimulationEvent? = null,
    val crossroad: CrossroadState = CrossroadState(),
) : SimulationState