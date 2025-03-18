;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include game.sh)
(use Main)
(use n021)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm300 0
)

(local
	noEntryMsg
	[plotString 301]
)
(instance rm300 of Room
	(properties
		picture 300
		east 220
	)
	
	(method (init)
		(super init:)
		(addToPics add: atpSign doit:)
		(aSpout init:)
		(self setScript: RoomScript)
		(NormalEgo)
		(if (== prevRoomNum 360)
			(ego posn: 155 190 setPri: 4 setLoop: 2)
			(RoomScript changeState: 1)
			(if (and larryBuffed (not (Btst fNotUseSoap)) (not (Btst fNotShower)))
				(Bset fBambiGone)
			)
		else
			(ego posn: 318 184 loop: 1)
		)
		(ego init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (& (ego onControl:) cYELLOW)
			(cond 
				((== currentStatus egoNORMAL)
					(cond
						((if (not (ego has: iTowel))
				;	((!= (ego has: iTowel)
						(Print 370 55) ;good
					 ))
					)
					(cond
						((if (not (ego has: iSoap))
						;((!= (ego has: iSoap)
						(Print 360 7) ;good
						))
					)
					(= currentStatus 300)
					(self changeState: 4)
				)
				
				((== currentStatus 301))
				((not noEntryMsg)
					(= noEntryMsg TRUE)
					(Print 300 0)
				)
			)
		)
	)
	
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0)
			(1
				(HandsOff)
				(= currentStatus 301)
				(ego illegalBits: 0 setMotion: MoveTo 155 160 self)
			)
			(2
				(ego setMotion: MoveTo 155 164 self)
			)
			(3
				(= currentStatus egoNORMAL)
				(NormalEgo)
			)
			(4
				(HandsOff)
				(ego
					illegalBits: 0
					setPri: 4
					setLoop: 3
					setMotion: MoveTo (ego x?) 200 self
				)
			)
			(5
				(= currentStatus egoNORMAL)
				(curRoom newRoom: 360)
			)
		)
	)
	
	(method (handleEvent event)
;;;		(if (or (!= (event type?) saidEvent) (event claimed?))
		(if (event claimed?)
			(return)
		)
		(if (Said 'look>')
			(cond 
				((Said '/fish')
					(Print 300 1)
				)
				((Said '/awning')
					(Print 300 2)
				)
				((Said '/panties')
					(Print 300 3)
				)
				((Said '/eye')
					(Print 300 4)
				)
				((Said '/lip')
					(Print 300 5)
				)
				((Said '/dicklicker')
					(Print 300 6)
				)
				((Said '/spout')
					(Print 300 7)
				)
				((Said '[/area]')
					(Print 300 8)
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
						(> (event x?) 300) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 169) ;y1
						(< (event y?) 189) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo 322 179)
						)
						(else
							(event claimed: FALSE)
						)
					)
			)
			
						(if
				(and ;background
						(> (event x?) 89) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 260) ;x2 
						(> (event y?) 50) ;y1
						(< (event y?) 135) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor
						(999

						)					
						(998 ; LOOK
							(switch (Random 1 5)
								(1 (Print 300 1))
								(2 (Print 300 2))
								(3 (Print 300 3))
								(4 (Print 300 5))
								(5 (Print 300 8))
							)
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

(instance atpSign of PicView
	(properties
		y 133
		x 62
		view 300
		priority 7
		signal ignrAct
	)
)

(instance aSpout of Prop
	(properties
		y 56
		x 157
		view 300
		loop 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15 setCycle: Forward)
	)
)
