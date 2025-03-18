;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include game.sh)
(use Main)
(use AutoDoor)
(use Intrface)
(use Game)
(use System)
(use Motion) ;added
(use User)

(public
	rm240 0
)

(local
	noEntryMsg
)
(instance rm240 of Room
	(properties
		picture 240
		horizon 66
		north 245
		east 245
		west 230
	)
	
	(method (init)
		(super init:)
		(self setScript: RoomScript)
		(aDoor
			locked: (if (!= currentStatus egoNORMAL) else playingAsPatti)
			init:
		)
		(cond 
			((== prevRoomNum 230)
				(ego x: 1)
			)
			((== prevRoomNum 340)
				(ego posn: 53 77 loop: 2)
			)
			(else
				(ego posn: 292 73)
			)
		)
		(NormalEgo)
		(ego init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
;;;		(DrawRect 15 257 84 175 10)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if
			(and
				(& (ego onControl:) cBLUE)
				(or playingAsPatti (!= currentStatus egoNORMAL))
			)
			(if (not noEntryMsg)
				(= noEntryMsg TRUE)
				(ego
					posn: (ego xLast?) (ego yLast?)
					setMotion: 0
					observeControl: cBLUE
				)
				(if playingAsPatti
					(Print 240 0)
				else
					(Print 240 1)
			)
				(Animate (cast elements?) FALSE)
			)
		else
			(= noEntryMsg FALSE)
		)
	)
	
	(method (handleEvent event)
		;(if (or (!= (event type?) saidEvent) (event claimed?))
		(if (event claimed?)
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/club,comedy,building')
					(Print 240 2)
				)
				((Said '/door')
					(if playingAsPatti
						(Print 240 3)
					else
						(Print 240 4)
					)
				)
				((Said '[/area]')
					(Print 240 5)
					(if playingAsPatti
						(Print 240 6)
					else
						(Print 240 7)
					)
				)
				)
			)
			(cond
			(
				(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))
			)
			
				(if
				(and ;look area 15 257 84 175
						(> (event x?) 15) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 257) ;x2 
						(> (event y?) 84) ;y1
						(< (event y?) 175) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor				
						(998 ; look door
							(Print 240 5)
							(if playingAsPatti
								(Print 240 6)
							else
							(Print 240 7)
							)
						)
						(else
							(event claimed: FALSE)
					 )
				)
				)
			
			(if
					(ClickedOnObj aDoor (event x?) (event y?))
					(event claimed: TRUE)
					(switch theCursor					
						(998 ; look door
							(if playingAsPatti
							(Print 240 3)
						else
							(Print 240 4)
							)
						)
						(995 ;use door. walk.
							(ego setMotion: MoveTo 50 50)
						)
					(else
						(event claimed: FALSE)
					 )
				
				)
				
			)
			
			
			(if
				(and ;exit room left
						(> (event x?) 1) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 11) ;x2 
						(> (event y?) 97) ;y1
						(< (event y?) 129) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor				
						(999 ; Walk
							(ego setMotion: MoveTo -5 120)
						)
					)
				)
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
		)
		
		
		
		
		
		
		
		
		
		
	)
))

(instance aDoor of AutoDoor
	(properties
		y 66
		x 53
		view 240
		entranceTo 340
	)
)