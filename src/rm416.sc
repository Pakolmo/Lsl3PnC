;;; Sierra Script 1.0 - (do not remove this comment)
(script# 416)
(include game.sh)
(use Main)
(use n021)
(use Extra)
(use Motion)
(use Game)
(use Actor)
(use System)
(use Intrface) ;ADD
(use User)

(public
	rm416 0
)

(instance rm416 of Room
	(properties
		picture 416
		east 450
		west 410
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
				(ego posn: 1 143)
			)
			((or (== currentStatus egoSHOWGIRL) (> (ego yLast?) 180))
				(ego posn: 315 136)
			)
			(else
				(ego posn: 290 122)
			)
		)
		(NormalEgo)
		(ego init:)
		(User canInput: FALSE mapKeyToDir: FALSE) ;add quitar teclado
		(aCraps init: isExtra: TRUE)
		(aCard init: isExtra: TRUE)
		(if (> machineSpeed 16) (aWalker init:) (aAlterEgo init:))
		
	)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(& (ego onControl:) cBLUE)
				(and (== currentStatus egoSHOWGIRL) (& (ego onControl:) cGREEN))
			)
			(curRoom newRoom: 450)
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
				(and ;mirror
						(> (event x?) 53) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 245) ;x2 
						(> (event y?) 21) ;y1
						(< (event y?) 113) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(998 ; LOOK
							(switch (Random 0 1)
						(1 (Print 417 8)
						(if (not (Btst fLookedInCasinoMirror))
							(Bset fLookedInCasinoMirror)
							(theGame changeScore: 2)
						)
						)
						(0 (Print 417 9))
						)	
				)
				(else
					(event claimed: FALSE)
				)
			)
						)
			
			
			
			(if
				(and ;exit1 right
						(> (event x?) 260) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 288) ;x2 
						(> (event y?) 78) ;y1
						(< (event y?) 128) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
								(ego setMotion: MoveTo 275 75)
							)
				(else
					(event claimed: FALSE)
				)
			)
			)
			
			
					(if
				(and ;exit left
						(> (event x?) 1) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 8) ;x2 
						(> (event y?) 123) ;y1
						(< (event y?) 157) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
								(ego setMotion: MoveTo -4 145)
							)
				(else
					(event claimed: FALSE)
				)
			)
			)	
			
		
			
						(if
				(and ;exit2 right 
						(> (event x?) 308) ;x1 (> (mouseX) (left edge of rectangle))
						(< (event x?) 319) ;x2 
						(> (event y?) 117) ;y1
						(< (event y?) 133) ;y2
					)
					(event claimed: TRUE)
					(switch theCursor					
						(999 ; WALK
					(ego setMotion: MoveTo 322 134)
			
			
			
						)
				(else
					(event claimed: FALSE)
				)
			)
						)))))
			


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
				(else
					(ego loop?)
				)
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
		y 60
		x 122
		view 414
		loop 3
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
		y 60
		x 160
		view 414
		loop 1
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
		view 415
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
							posn: 72 15
							setPri: 1
							setMotion: MoveTo 198 15 self
						)
					)
					(1
						(aWalker
							view: (Random 415 419)
							posn: 198 15
							setPri: 1
							setMotion: MoveTo 72 15 self
						)
					)
					(2
						(aWalker
							view: 415
							posn: 132 32
							setPri: 0
							setMotion: MoveTo 132 142 self
						)
					)
					(3
						(aWalker
							view: 415
							posn: 132 142
							setPri: 0
							setMotion: MoveTo 132 32 self
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
