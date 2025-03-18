;;; Sierra Script 1.0 - (do not remove this comment)
(script# 245)
(include game.sh)
(use Main)
(use Intrface)
(use Game)
(use System)
(use Motion) ;add
(use User)

(public
	rm245 0
)

(local
	noEntryMsg
)
(instance rm245 of Room
	(properties
		picture 245
		west 240
	)
	
	(method (init)
		(super init:)
		(self setRegions: FALLING setScript: RoomScript)
		(if (== prevRoomNum 500)
			(ego posn: 126 73 loop: 2)
		else
			(ego posn: 5 172 loop: 0)
		)
		(NormalEgo)
		(ego init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
		(if currentStatus
			(ego observeControl: cGREEN cCYAN cRED)
		)
	)
	
	(method (newRoom n)
		
		(cls)
		(super newRoom: n)
	)
)

(instance RoomScript of Script	
	(method (doit)
		(if (not playingAsPatti)
			(ego observeControl: cYELLOW)
			(if (& (ego onControl:) cMAGENTA)
				(if (not noEntryMsg)
					(= noEntryMsg TRUE)
					(Print 245 0)
				)
			else
				(= noEntryMsg FALSE)
			)
		)
		(if (== currentStatus egoNORMAL)
			(cond

				((& (ego onControl:) cBLUE)
				(if (not (ego has: iWineBottle))
						(Print {&Llevas agua para el camino?}) ;spanish
;;;						(Print {Do you have water for the road?}) ;english
				) 
				(if (not (ego has: iPanties))
						(Print {Vas a necesitar toda tu ropa.}) ;spanish
;;;						(Print {You're going to need all your clothes.}) ;english
				)
				(if (not (ego has: iBra))
						(Print {Olvidaste ponerte el sujetador.}) ;spanish
;;;						(Print {You forgot to put on your bra.}) ;english
				)
                (if (not (ego has: iMarker))
                         (Print {Vas a necesitar algo m*gico.}) ;spanish
;;;                         (Print {You will need some of magic...}) ;english
                )				
					
					
					
					
					
					(curRoom newRoom: 500)
				)
				((& (ego onControl:) cRED)
					(NotifyScript FALLING 9 300)
				)
				((& (ego onControl:) cGREEN)
					(NotifyScript FALLING 0 300)
				)
				((or (== EAST (ego edgeHit?)) (& (ego onControl:) cCYAN))
					(ego x: (+ (ego x?) 8))
					(NotifyScript FALLING 8 300)
				)
			)
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		;(if (or (!= (event type?) saidEvent) (event claimed?))
		(if (event claimed?)
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/ball,boulder,boob')
					(Print 245 1)
				)
				((Said '/bamboo')
					(Print 245 2)
				)
				((Said '[/area]')
					(Print 245 3)
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
				(and ;exit room
						(> (event x?) 1) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 16) ;x2 
						(> (event y?) 157) ;y1
						(< (event y?) 189) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
									(ego setMotion: MoveTo -2 177)
						)
						(else
							(event claimed: FALSE)
						)
					)
						)

						(if
				(and ;bambu
						(> (event x?) 128) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 207) ;x2 
						(> (event y?) 21) ;y1
						(< (event y?) 65) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(998 ; Look
									(Print 245 2)
						)
						(else
							(event claimed: FALSE)
						)
					)
						)
		 		
		
		
		
								(if
				(and ;area background
						(> (event x?) 10) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 69) ;y1
						(< (event y?) 189) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(998 ; Look
									(Print 245 3)
						)
						(else
							(event claimed: FALSE)
						)
					)
						)
				
		
		
		
										(if
				(and ;rock
						(> (event x?) 208) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 256) ;x2 
						(> (event y?) 28) ;y1
						(< (event y?) 69) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(998 ; Look
									(Print 245 1)
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