from kivy.app import App
from kivy.uix.button import Button
from kivy.uix.gridlayout import GridLayout
from kivy.uix.label import Label
from kivy.clock import Clock
import math

from src import Main, StateMachine

timeKept = 0
timerRunning = False

# Callable functions

def getTimerState():
    if Main.timerRunning:
        return "running"
    else:
        return "stopped"

# Returns time on timer in milliseconds
def matchButtons():
    if Main.robotEnabled:
        modeMenu.enableLabel.text = "Enabled"
    else:
        modeMenu.enableLabel.text = "Disabled"

    if Main.gameMode == "tele":
        modeMenu.stateLabel.text = "TeleOp"
    else:
        modeMenu.stateLabel.text = "Autonomous"

    if Main.started:
        startButton.text = "Stop"
    else:
        startButton.text = "Start"

def getTime():
    return round(Main.timeKept * 1000)

def enable(self):
    Main.robotEnabled = True
    modeMenu.enableLabel.text = "Enabled"

def disable(self):
    Main.robotEnabled = False
    modeMenu.enableLabel.text = "Disabled"

def auto(self):
    Main.gameMode = "auto"
    modeMenu.stateLabel.text = "Autonomous"

def tele(self):
    Main.gameMode = "tele"
    modeMenu.stateLabel.text = "TeleOp"

# Call this to start the application
def launch():
    GUIApp().run()

class TimerClock(Label):

    def __init__(self, **kwargs):
        super(TimerClock, self).__init__(**kwargs)
        self.text = "00:00.0"
        self.font_size = 100
        Clock.schedule_interval(self.update, 0.1)

    # Formats Main.timeKept (seconds) to minutes:seconds
    def update(self, dt):
        if Main.timerRunning: Main.timeKept += dt
        remainingTime = 0
        if(Main.gameMode == "auto"):
            remainingTime = Main.autoSeconds - Main.timeKept
        else:
            remainingTime = Main.teleSeconds- Main.timeKept
        deciseconds = str(math.floor(remainingTime * 10) % 10)
        seconds = str(math.floor(remainingTime) % 60).zfill(2)
        minutes = str(math.floor(remainingTime / 60) % 100).zfill(2)
        self.text = '{0}:{1}.{2}'.format(minutes, seconds, deciseconds)
        Main.mainLoop()
        matchButtons()

timerclock = TimerClock()

class StartButton(Button):

    def __init__(self, **kwargs):
        super(StartButton, self).__init__(**kwargs)
        self.text = 'Start'
        self.font_size = '72'
        self.bind(on_press = lambda self: self.toggle())

    def toggle(self):
        Main.timerRunning = not Main.timerRunning
        Main.started = not Main.started
        if not Main.started:
            StateMachine.stop()
            Main.started = False
        else:
            StateMachine.start()
            Main.started = True

# Button will reset timer to 0 (whether timer is running or not)
class ResetButton(Button):
    def __init__(self, **kwargs):
        super(ResetButton, self).__init__(**kwargs)
        self.text = 'Reset'
        self.font_size = '72'
        self.bind(on_press = lambda self: self.reset())

    def reset(self):
        StateMachine.reset()

startButton = StartButton()

# Contains both the start and reset buttons
class TimerButtons(GridLayout):

    def __init__(self, **kwargs):
        super(TimerButtons, self).__init__(**kwargs)
        self.cols = 2
        self.add_widget( startButton )
        self.add_widget( ResetButton() )

# The class for a button (used for styling)
class GenericButton(Button):

    def __init__(self, **kwargs):
        super(GenericButton, self).__init__(**kwargs)
        self.font_size = 48

# The button responsible for starting and stopping the timer


# Includes enable/disable and tele/auto
class ModeMenu(GridLayout):

    enableLabel = Label( text = "Disabled", font_size = 48)
    stateLabel = Label( text = "TeleOp", font_size = 48)

    def __init__(self, **kwargs):
        super(ModeMenu, self).__init__(**kwargs)
        self.cols = 2
        self.add_widget( self.enableLabel )
        self.add_widget( self.stateLabel )

        enableButton = GenericButton(text = 'Enable')
        disableButton = GenericButton(text = 'Disable')
        autoButton = GenericButton(text = 'Autonomous')
        teleButton = GenericButton(text = 'TeleOp')

        enableButton.bind(on_press = enable)
        disableButton.bind(on_press = disable)
        autoButton.bind(on_press = auto)
        teleButton.bind(on_press = tele)

        self.add_widget( enableButton )
        self.add_widget( autoButton )
        self.add_widget( disableButton )
        self.add_widget( teleButton )
modeMenu = ModeMenu()

class AppLayout(GridLayout):

    def __init__(self, **kwargs):
        super(AppLayout, self).__init__(**kwargs)
        self.cols = 1
        self.add_widget( timerclock )
        self.add_widget(TimerButtons())
        self.add_widget( modeMenu )

class GUIApp(App):
    def build(self):
        return AppLayout()

launch()