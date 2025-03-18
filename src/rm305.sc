;;; Sierra Script 1.0 - (do not remove this comment)
(script# 305)
(include game.sh)
(use Main)
(use n021)
(use AutoDoor)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)
(use User)

(public
	rm305 0
)

(local
	[plotString 307]
)
(instance rm305 of Room
	(properties
		picture 305
		east 250
		west 350
	)
	
	(method (init)
		(super init:)

				
		(addToPics
			add: atpPalmTree1
			add: atpPalmTree2
			add: atpPalmTree3
			add: atpPalmTree3a
			add: atpPalmTree4
			add: atpPalmTree5
			add: atpPalmTree6
			add: atpPalmTree7
			add: atpPalmTree8
			add: atpPalmTree9
			add: atpPalmTree10
			doit:
		)
		(if (> machineSpeed 16)
			(aSign setPri: 12 setCycle: Forward isExtra: TRUE init:)
		)
		(aDoor init:)
		(self setScript: RoomScript)
		(if (== prevRoomNum 350)
			(ego posn: 78 144 loop: 0)
		else
			(ego posn: 313 161)
		)
		(NormalEgo)
		(ego init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(if (== currentStatus egoROLLOUT)
					(Bset fFired)
					(= currentStatus egoNORMAL)
					(= state 1)
					(= cycles 20)
				)
			)
			(1)
			(2
				(Print 305 5)
			)
		)
	)
	
	(method (handleEvent event)
	;	(if (or (!= (event type?) saidEvent) (event claimed?))
		(if (event claimed?)
			(return)
		)
		(cond 
			((Said 'look<in/cup')
				(Print 305 0)
			)
			((Said 'look>')
				(cond 
					((Said '/awning')
						(Print 305 1)
					)
					((Said '/palm,palm')
						(Print 305 2)
					)
					((Said '[/building,area]')
						(Print 305 3)
						(if (not playingAsPatti)
							(Print 305 4 #at -1 144)
						)
					)
				)
			)
			
			(
				(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))
			)	
				(if
					(and ;exitroom to fountain path 300 319 140 180 2
						(> (event x?) 300)  ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 140) ;y1
						(< (event y?) 180) ;y1
					)
				(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo 324 171)
						)
						(else
							(event claimed: FALSE)
						)
					)
					
			
				)
			
				(if ;check if clicked on atpBinocular1
					(or
						(ClickedOnObj atpPalmTree1 (event x?) (event y?))
						(ClickedOnObj atpPalmTree2 (event x?) (event y?))
						(ClickedOnObj atpPalmTree3 (event x?) (event y?))
						(ClickedOnObj atpPalmTree3a (event x?) (event y?))
						(ClickedOnObj atpPalmTree4 (event x?) (event y?))
						(ClickedOnObj atpPalmTree5 (event x?) (event y?))
						(ClickedOnObj atpPalmTree6 (event x?) (event y?))						
						(ClickedOnObj atpPalmTree7 (event x?) (event y?))
						(ClickedOnObj atpPalmTree8 (event x?) (event y?))
						(ClickedOnObj atpPalmTree9 (event x?) (event y?))						
						(ClickedOnObj atpPalmTree10 (event x?) (event y?))
					)
					(event claimed: TRUE)

					
					(switch theCursor
						(998 ;look
							(switch (Random 1 2)
							(1 (Print 305 2)
							)
							(2 
								(Print 305 3)
								(if (not playingAsPatti)
									(Print 305 4 #at -1 144)
								)
							)
							)
						)(else
						(event claimed: FALSE)
					)
					)
				)
			
			
					(if ;check if clicked on atpBinocular1
							(ClickedOnObj aSign (event x?) (event y?))
							(event claimed: TRUE)
							(switch theCursor
								(998 ;look
									(Print 305 1)
								)
								(else
									(event claimed: FALSE)
								)
							)
					)
			
			)
			
		)
	)
)

(instance atpPalmTree1 of PicView
	(properties
		y 128
		x 143
		view 305
		loop 1
		priority 9
	)
)

(instance atpPalmTree2 of PicView
	(properties
		y 131
		x 183
		view 305
		loop 1
		priority 9
	)
)

(instance atpPalmTree3 of PicView
	(properties
		y 135
		x 225
		view 305
		loop 1
		priority 9
	)
)

(instance atpPalmTree3a of PicView
	(properties
		y 139
		x 266
		view 305
		loop 1
		priority 9
	)
)

(instance atpPalmTree4 of PicView
	(properties
		y 144
		x 311
		view 305
		loop 1
		priority 10
	)
)

(instance atpPalmTree5 of PicView
	(properties
		y 161
		x 42
		view 305
		loop 1
		cel 1
		priority 13
		signal ignrAct
	)
)

(instance atpPalmTree6 of PicView
	(properties
		y 169
		x 90
		view 305
		loop 1
		cel 1
		priority 14
		signal ignrAct
	)
)

(instance atpPalmTree7 of PicView
	(properties
		y 175
		x 140
		view 305
		loop 1
		cel 1
		priority 15
		signal ignrAct
	)
)

(instance atpPalmTree8 of PicView
	(properties
		y 180
		x 190
		view 305
		loop 1
		cel 1
		priority 15
		signal ignrAct
	)
)

(instance atpPalmTree9 of PicView
	(properties
		y 185
		x 238
		view 305
		loop 1
		cel 1
		priority 15
		signal ignrAct
	)
)

(instance atpPalmTree10 of PicView
	(properties
		y 192
		x 287
		view 305
		loop 1
		cel 1
		priority 15
		signal ignrAct
	)
)

(instance aSign of Prop
	(properties
		y 60
		x 149
		view 305
		loop 2
	)
)

(instance aDoor of AutoDoor
	(properties
		y 86
		x 93
		view 305
		entranceTo 350
	)
	
	(method (init)
		(super init:)
		(self setPri: 5)
	)
)
