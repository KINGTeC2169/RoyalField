from src import GPIOHandler, StateMachine

autoSeconds = 15
teleSeconds = 120
gameMode = "auto"
robotEnabled = False
timeKept = 0
timerRunning = False
started = False

def mainLoop():
    global gameMode
    global robotEnabled
    global timeKept
    if gameMode == "auto" and timeKept >= autoSeconds:
        StateMachine.stop()
        robotEnabled = False
        gameMode = "tele"
    if gameMode == "tele" and timeKept >= teleSeconds:
        StateMachine.stop()
        robotEnabled = False
        gameMode = "auto"

    GPIOHandler.handleOutputs(gameMode, robotEnabled)


