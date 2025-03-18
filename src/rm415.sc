;;; Sierra Script 1.0 - (do not remove this comment)
(script# 415)
(include game.sh)
(use Main)
(use n021)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)
(use Intrface)
(use User)

(public
	rm415 0
)

(instance rm415 of Room
	(properties
		picture 415
		east 410
		west 420
	)
	
	(method (init)
		(Load VIEW 415)
		(Load VIEW 416)
		(Load VIEW 417)
		(Load VIEW 418)
		(Load VIEW 419)
		(super init:)
		(self setRegions: CASINO_MIRROR)
		(cond 
			((== prevRoomNum 410)
				(ego posn: 318 143)
			)
			((or (== currentStatus egoSHOWGIRL)(> (ego y?) 180))
				(ego posn: 1 143)
			)
			(else
				(ego posn: 31 122)
			)
		)
		(NormalEgo)
		(ego init:)
		(aCraps init: isExtra: TRUE)
		(aCard init: isExtra: TRUE)
		(if (> machineSpeed 16)
			(aWalker init:)
			(aAlterEgo init:)
		)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
;;;		(DrawRect 5 24 112 142 11)
;;;		(DrawRect 300 319 124 166 12)
;;;		(DrawRect 24 300 21 190 9)
;;;		(DrawRect 74 270 21 115 8)
			
	)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(& (ego onControl:) cBLUE)
				(and (== currentStatus egoSHOWGIRL) (& (ego onControl:) cGREEN))
			)
			(curRoom newRoom: 420)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) ;only check if claimed
			(return)
		)
		(cond
			(
				(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emRIGHT_BUTTON))
			)
			(if
			(event claimed: TRUE)
			(switch theCursor
				(998 ;look
					(if (ClickedInRect 24 300 21 190 event)
						(Print 499 6)	
					)	
				)
				(else
					(event claimed: FALSE)
				)
			)
		)
		(if 
				(and ;exit room left 5 24 112 142
						(> (event x?) 5) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 24) ;x2 
						(> (event y?) 112) ;y1
						(< (event y?) 142) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo -3 125)
						)
						(else
							(event claimed: FALSE)
						)
					)

				)
		(if 

				(and ;exit room right 300 319 124 166
						(> (event x?) 300) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 124) ;y1
						(< (event y?) 166) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
							(ego setMotion: MoveTo 322 147)
						)
						(else
							(event claimed: FALSE)
						)
					)
				
		)
		(if 
				(and ;mirror 74 270 21 115 
						(> (event x?) 74) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 270) ;x2 
						(> (event y?) 21) ;y1
						(< (event y?) 115) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(998 ; Look
							
							(Print 417 8)
							(if (not (Btst fLookedInCasinoMirror))
								(Bset fLookedInCasinoMirror)
								(theGame changeScore: 2)
							)
						)
						(else
							(event claimed: FALSE)
						)
					)
						)

)))
	
)

(instance aAlterEgo of Actor
	(properties
		view 700
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			view: (ego view?)
			setPri: 4
			posn: (ego x?) 122
			ignoreHorizon:
			ignoreActors:
		)
	)
	
	(method (doit)
		(aAlterEgo
			view: (ego view?)
			loop:
			(switch (ego loop?)
				(loopN loopS)
				(loopS loopN)
				(else  (ego loop?))
			)
			cel: (ego cel?)
			x: (ego x?)
			y: (- 125 (/ (- (ego y?) 127) 2))
		)
		(super doit:)
	)
)

(instance aCraps of Extra
	(properties
		y 62
		x 198
		view 414
		loop 2
		cel 11
		cycleSpeed 0
		cycleType ExtraEndLoop
		hesitation 11
		pauseCel 11
		minPause 22
		maxPause 111
	)
)

(instance aCard of Extra
	(properties
		y 59
		x 160
		view 414
		minPause 11
		maxPause 55
		minCycles 15
		maxCycles 44
	)
)

(instance aWalker of Actor
	(properties
		y 15
		x 119
		view 414
		illegalBits $0000
	)
	
	(method (init)
		(super init:)
		(self
			ignoreActors:
			setCycle: Walk
			setStep: 2 1
			setScript: WalkerScript
		)
	)
)

(instance WalkerScript of Script
	(method (changeState newState)
		(ChangeScriptState self newState 1 2)
		(switch (= state newState)
			(0
				(= seconds (Random 2 6))
			)
			(1
				(switch (Random 0 6)
					(0
						(aWalker
							view: (Random 415 419)
							posn: 251 15
							setPri: 1
							setMotion: MoveTo 119 15 self
						)
					)
					(1
						(aWalker
							view: (Random 415 419)
							posn: 119 15
							setPri: 1
							setMotion: MoveTo 251 15 self
						)
					)
					(2
						(aWalker
							view: 415
							posn: 186 33
							setPri: 0
							setMotion: MoveTo 186 142 self
						)
					)
					(3
						(aWalker
							view: 415
							posn: 186 142
							setPri: 0
							setMotion: MoveTo 186 33 self
						)
					)
					(else
						(= seconds 2)
					)
				)
				(= state -1)
			)
		)
	)
)
