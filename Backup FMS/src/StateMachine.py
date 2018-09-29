from src import Main


def reset():
    Main.timeKept = 0
    Main.timerRunning = False
    if(Main.gameMode == "tele"):
        Main.gameMode = "auto"
    else:
        Main.gameMode = "tele"
    stop()

def start():
    Main.timerRunning = True
    Main.robotEnabled = True
    Main.started = True

def stop():
    Main.timeKept = 0
    Main.timerRunning = False
    Main.robotEnabled = False
    Main.started = False
