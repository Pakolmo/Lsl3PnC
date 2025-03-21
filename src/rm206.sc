;;; Sierra Script 1.0 - (do not remove this comment)
(script# 206)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm206 0
)

(local
	local0
	girlStage
)
(instance rm206 of Room
	(properties
		picture 206
	)
	
	(method (init)
		(HandsOff)
		(User canControl: TRUE)
		(User canInput: TRUE)
		;(Bset fCursorHidden) keep cursor visible
		(= theCursor 998) ;set to look cursor
		(theGame setCursor: 998) ;set to look cursor
		(= oldStatus currentStatus)
		(= currentStatus curRoomNum)
		(Load SOUND 206)
		(super init:)
		(self setScript: RoomScript)
		(aShade1 init:)
		(aShade2 init:)
		(aShade3 init:)
		(aGull init:)
		(aGirl init:)
		(= saveSpeed (theGame setSpeed: 6))
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= girlStage 1)
				(= seconds 3)
			)
			(1
				(aGirl setMotion: MoveTo 10 109 self)
				(= cycles 11)
			)
			(2
				(music number: 206 loop: -1 play:)
			)
			(3
				(addToPics
					add: atpWall1
					add: atpWall2
					add: atpWall3
					doit:
				)
				(= cycles 3)
				(= girlStage 2)
			)
			(4
				(aGirl setCycle: Walk setMotion: MoveTo 111 109 self)
			)
			(5
				(aGirl
					setLoop: 4
					setCel: 0
					setCycle: EndLoop self
					cycleSpeed: 1
				)
			)
			(6
				(= girlStage 3)
				(aGirl
					setLoop: 5
					setCel: 0
					setCycle: EndLoop self
					cycleSpeed: 1
				)
			)
			(7
				(aGirl setCycle: BegLoop cycleSpeed: 3)
				(aShade1
					setMotion: MoveTo 113 (- 102 (* 8 filthLevel)) self
				)
			)
			(8
				(aShade1 stopUpd:)
				(aGirl
					setLoop: 4
					setCel: 255
					setCycle: BegLoop self
					cycleSpeed: 1
				)
			)
			(9
				(aGirl
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 162 109 self
					cycleSpeed: 0
				)
			)
			(10
				(aGirl
					setLoop: 4
					setCel: 0
					setCycle: EndLoop self
					cycleSpeed: 1
				)
			)
			(11
				(aGirl
					setLoop: 5
					setCel: 0
					setCycle: EndLoop self
					cycleSpeed: 1
				)
			)
			(12
				(aGirl setCycle: BegLoop cycleSpeed: 3)
				(aShade2
					setMotion: MoveTo 169 (- 102 (* 8 filthLevel)) self
				)
			)
			(13
				(aShade2 stopUpd:)
				(aGirl
					setLoop: 4
					setCel: 255
					setCycle: BegLoop self
					cycleSpeed: 1
				)
			)
			(14
				(aGirl
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 220 109 self
					cycleSpeed: 0
				)
			)
			(15
				(aGirl
					setLoop: 4
					setCel: 0
					setCycle: EndLoop self
					cycleSpeed: 1
				)
			)
			(16
				(aGirl
					setLoop: 5
					setCel: 0
					setCycle: EndLoop self
					cycleSpeed: 1
				)
			)
			(17
				(aGirl setCycle: BegLoop cycleSpeed: 3)
				(aShade3
					setMotion: MoveTo 225 (- 102 (* 8 filthLevel)) self
				)
			)
			(18
				(aShade3 stopUpd:)
				(= cycles 22)
			)
			(19
				(aGirl
					setLoop: 1
					setCycle: Walk
					setMotion: MoveTo 169 109 self
					cycleSpeed: 0
				)
			)
			(20
				(= girlStage 4)
				(aGirl
					setLoop: 6
					setCel: 0
					setCycle: EndLoop self
					cycleSpeed: 1
				)
			)
			(21
				(= cycles (+ 8 filthLevel))
			)
			(22
				(aGirl setLoop: 7 setCel: 0 setCycle: EndLoop self)
			)
			(23
				(= cycles (+ 8 filthLevel))
			)
			(24
				(= girlStage 5)
				(music fade:)
				(aGirl
					setLoop: 2
					setCycle: Walk
					setMotion: MoveTo 300 109 self
					cycleSpeed: 0
				)
			)
			(25
				(= girlStage 6)
				(= seconds 3)
			)
			(26
				(Bset fLookedInBinoculars)
				(theGame changeScore: 2 setSpeed: saveSpeed)
				
				(Print 206 15)
				(HandsOn)
				;(= currentStatus oldStatus)
				(theGame setCursor: 998 (HaveMouse))
				(curRoom newRoom: 200)
				
				
				
				
			)
		)
	)
	
	(method (handleEvent event)
		;(if (or (!= (event type?) saidEvent) (event claimed?))
		(if (event claimed?) ;only return if event claimed, let mouse events pass	
			(return)
		)
		(cond 
			(
				(or
					(Said 'look/area')
					(Said 'look<cease,away')
					(Said 'cease/look')
					(Said 'look<cease/awning')
					(Said 'exit,cease')
					(Said 'backdrop/binocular')
				)
				(Ok)
				(= currentStatus oldStatus)
				(curRoom newRoom: 200)
			)
			((Said 'hear/babe')
				(Print 206 0)
			)
			((Said 'address/babe')
				(Print 206 1)
			)
			((Said '/panties,panties')
				(Print 206 2)
			)
			((Said 'drag/binocular')
				(Print 206 3)
				(Print 206 4 #at -1 144)
			)
			((Said 'look<in/binocular')
				(Print 206 5)
			)
			((Said 'look/area')
				(Print 206 6)
				(Print 206 7 #at -1 144)
			)
			((Said 'look[/babe]')
				(switch girlStage
					(1 (Printf 206 8 currentEgo))
					(2 (Print 206 9))
					(3 (Print 206 10))
					(4 (Print 206 11))
					(5 (Print 206 12))
					(6 (Print 206 13))
					(else  (Print 206 14))
				)
			)
			((== (event type?) evMOUSEBUTTON)
				(event claimed: 1)
				(if (& (event modifiers?) emRIGHT_BUTTON) ;is right_click
;;;					(if (== theCursor 998) ;toggle between exit and look
;;;						(theGame setCursor: 991) ; switch to exit cursor	
;;;					else
;;;						(theGame setCursor: 998) ; switch to look cursor
;;;					)
					(switch theCursor ;current cursor
						(itemIcon
							(theGame setCursor: 991 (HaveMouse)) ;switch to exit intead of walk
							(event claimed: 1)
						)
						(991 ;if exit
							(theGame setCursor: 998 (HaveMouse))
							(event claimed: 1)
												

					
							
							
							
						)
						(999 ;just incase they have the walk cursor
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(993 ;just incase they have the arrow cursor
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(996
							(theGame setCursor: 994 (HaveMouse))
							(event claimed: 1)
						)
						(998
							(theGame setCursor: 995 (HaveMouse))
							(event claimed: 1)
						)
						(995
							(theGame setCursor: 996 (HaveMouse))
							(event claimed: 1)
						)
						(994
							(if
								(or 
									(== itemIcon 900)
									(== itemIcon 994)
									(== itemIcon 666)
									(== itemIcon 992)
									(== itemIcon 993)
								)
								(theGame setCursor: 991 (HaveMouse))
							else
								(theGame setCursor: itemIcon (HaveMouse))
								(= theCursor itemIcon)
							)
							(event claimed: 1)
						)
					)                                                                  
				else ;left clicks
					(if (== theCursor 998)
						(if (ClickedOnObj aGirl (event x?) (event y?))
							(switch girlStage
								(1 (Printf 206 8 currentEgo))
								(2 (Print 206 9))
								(3 (Print 206 10))
								(4 (Print 206 11))
								(5 (Print 206 12))
								(6 (Print 206 13))
								(else  (Print 206 14))
							)
						else
							(Print 206 6)
							(Print 206 7 #at -1 144)
						)
					)
					(if (== theCursor 991) ;exit
						(Ok)
						(theGame setCursor: 998 setSpeed: saveSpeed) ;set cursor to look before returning
						(theGame setCursor: 998 (HaveMouse))
						(event claimed: 1)
						(= currentStatus oldStatus)
						(curRoom newRoom: 200)
					)
					(if (== theCursor 996) ;talk
						(Print 206 0)
					)
				)
			)
					(else
						(event claimed: FALSE)
					)
					
		
	
)))
(instance aShade1 of Actor
	(properties
		y 64
		x 113
		view 206
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setLoop: 0 setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aShade2 of Actor
	(properties
		y 64
		x 169
		view 206
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setLoop: 0 setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance aShade3 of Actor
	(properties
		y 64
		x 225
		view 206
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setLoop: 0 setPri: 11 ignoreActors: stopUpd:)
	)
)

(instance atpWall1 of PicView
	(properties
		y 109
		x 113
		view 206
		loop 1
	)
)

(instance atpWall2 of PicView
	(properties
		y 109
		x 169
		view 206
		loop 2
	)
)

(instance atpWall3 of PicView
	(properties
		y 109
		x 225
		view 206
		loop 3
	)
)

(instance aGirl of Actor
	(properties
		y 109
		x 263
		view 207
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self setCycle: Walk ignoreActors:)
	)
)

(instance aGull of Actor
	(properties
		y 70
		x -66
		view 208
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			setPri: 14
			setCycle: Forward
			ignoreActors:
			setMotion: MoveTo 319 44
		)
	)
)