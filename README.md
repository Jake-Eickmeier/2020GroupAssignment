# 2020GroupAssignment
CSCI 2020U Final group project

IDEAS:

	For multiplayer:
	 - Create or join rooms
	 - Room is a gridpane with axb slots, people instantiated randomly in free slot
	 - Unless time is permitted, only your own avatar follows movement (others are image)
	 - Chat room on the side
	



	 - Can share high score? (will want to encrypt high score file for this so can't adjust manually)
	 
	For bullets:
	 - Bullet class instantiated by method within Gun class
	 - Bullet gets "deleted" when just outside of window border
	 - Several bullets allowed at once but locked by "timer" of some sort, i.e. can only shoot bullets once every n seconds
	 
	For binary I/O:
	 - File to contain profile/avatar selection info, high score
	-Files contained in order of (username: String,eye: int, mouth: int, Colour: String)