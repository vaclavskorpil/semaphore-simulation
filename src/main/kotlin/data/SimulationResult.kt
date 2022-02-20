package data

import base.SimulationEvent
import base.SimulationState

class SimulationResult<State : SimulationState, Event : SimulationEvent>(
    val eventTimeline: List<Event>,
    val simulationStateTimeline: List<State>,
)