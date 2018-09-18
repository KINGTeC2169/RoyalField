from kivy.app import App
from kivy.uix.widget import Widget
from kivy.uix.button import Button
from kivy.uix.gridlayout import GridLayout
from kivy.uix.label import Label
from kivy.clock import Clock
import math
import time

timeKept = 0
timerRunning = False
robotState = "tele"
robotEnabled = False

# Callable functions

def getTimerState():
    if timerRunning:
        return "running"
    else:
        return "stopped"

# Returns time on timer in milliseconds
def getTime():
    return round(timeKept * 1000)

# Returns strings "tele" or "auto"
def getRobotState():
    return robotState

def enable(self):
    robotEnabled = True
    modeMenu.enableLabel.text = "Enabled"

def disable(self):
    robotEnabled = False
    modeMenu.enableLabel.text = "Disabled"

def auto(self):
    robotState = "auto"
    modeMenu.stateLabel.text = "Autonomous"

def tele(self):
    robotState = "tele"
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

    # Formats timekept (seconds) to minutes:seconds
    def update(self, dt):
        global timeKept
        if(timerRunning): timeKept += dt
        deciseconds = str(math.floor(timeKept * 10) % 10)
        seconds = str(math.floor(timeKept) % 60).zfill(2)
        minutes = str(math.floor(timeKept / 60) % 100).zfill(2)
        self.text = '{0}:{1}.{2}'.format(minutes, seconds, deciseconds)
timerclock = TimerClock()

# Contains both the start and reset buttons
class TimerButtons(GridLayout):

    def __init__(self, **kwargs):
        super(TimerButtons, self).__init__(**kwargs)
        self.cols = 2
        self.add_widget( StartButton() )
        self.add_widget( ResetButton() )

# The class for a button (used for styling)
class GenericButton(Button):

    def __init__(self, **kwargs):
        super(GenericButton, self).__init__(**kwargs)
        self.font_size = 48

# The button responsible for starting and stopping the timer
class StartButton(Button):

    def __init__(self, **kwargs):
        super(StartButton, self).__init__(**kwargs)
        self.text = 'Start'
        self.font_size = '72'
        self.bind(on_press = lambda self: self.toggle())

    def toggle(self):
        global timerRunning
        global timeKept
        timerRunning = not timerRunning
        if timerRunning:
            self.text = 'Stop'
        else:
            self.text = 'Start'

# Button will reset timer to 0 (whether timer is running or not)
class ResetButton(Button):
    def __init__(self, **kwargs):
        super(ResetButton, self).__init__(**kwargs)
        self.text = 'Reset'
        self.font_size = '72'
        self.bind(on_press = lambda self: self.reset())

    def reset(self):
        global timeKept
        timeKept = 0

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
        self.add_widget( TimerButtons() )
        self.add_widget( modeMenu )

class GUIApp(App):
    def build(self):
        return AppLayout()
