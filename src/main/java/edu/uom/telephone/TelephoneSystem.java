package  edu.uom.telephone;

class TelephoneSystem {
	private boolean onHook = true, ringing = false, dialTone = false, inCall = false;

	boolean isOnHook() {
		return onHook;
	}

	boolean isRinging() {
		return ringing;
	}

	boolean isDialTone() {
		return dialTone;
	}

	boolean isInCall() {
		return inCall;
	}

	void incomingCall() {
		if (onHook && !ringing && !dialTone && !inCall) {
			ringing = true;
		}
	}

	void stopRinging() {
		if (onHook && ringing && !dialTone && !inCall) {
			ringing = false;
		}
	}

	void answerCall() {
		if (onHook && ringing && !dialTone && !inCall) {
			onHook = false;
			ringing = false;
			inCall = true;
		}
	}

	void liftReceiver() {
		if (onHook && !ringing && !dialTone && !inCall) {
			onHook = false;
			dialTone = true;
		}
	}

	void call(final int recipient) {
		if (!onHook && !ringing && dialTone && !inCall) {
			dialTone = false;
			inCall = true;
		}
	}

	void putDownReceiver() {
		if (!onHook && !ringing && dialTone && !inCall) {
			onHook = true;
			dialTone = false;
		} else if (!onHook && !ringing && !dialTone && inCall) {
			onHook = true;
			inCall = false;
		}
	}
}
