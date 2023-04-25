import { createStore } from 'redux';
import Cookies from "js-cookie";

// some settings shouldn't be saved persistently
export const UNSAVED_SETTINGS = [
    "voicechatMonitoringEnabled",
    "voicechatMuted"
]

const initialState = {
    // state - null for the login screen
    currentUser: null,

    debug: false,

    relay: {
        endpoint: null,
        connected: false,
        connecting: false,
    },

    isPremium: false,
    browserSupportsVoiceChat: false,
    bucketFolder: null,

    inputModal: {
        visible: false,
        title: '',
        message: '',
        callback: null,
    },

    settings: {
        prefetchMedia: true,
        normalVolume: 35,
        voicechatVolume: 100,
        voicechatMuted: false,
        voicechatSurroundSound: true,
        voicechatMonitoringEnabled: false,
        microphoneSensitivity: 75,
        automaticSensitivity: false,
        fadeAudio: true,

        voicechatChimesEnabled: true,
        interpolationEnabled: true,
        streamermodeEnabled: false,
        spatialRenderingMode: 'new',
        rolloffFactor: .5,

        preferredMicId: "default",
    },

    loadingOverlay: {
        visible: false,
        title: null,
        message: null,
        footer: null,
    },

    soundcloud: {
        visible: false,
        title: 'Cool song song',
        image: 'https://i1.sndcdn.com/artworks-NWsyJg2rpTy2imze-4ttQKA-t500x500.jpg',
        link: 'https://soundcloud.com/cool-songs/cool-song-song',
    },

    voiceState: {
        isModerating: false,
        isTemporarilyDisabled: false,
        enabled: false,
        ready: false,
        isSpeaking: false,
        serverHasModeration: false,
        streamServer: null,
        streamKey: null,
        radius: 25,
        mics: [], // cached list of microphones
        peers: {
            'dd': {
                name: 'ludwigahgren',
                uuid: "37784d2d-5c4a-40a3-9b13-89baa9eba0d7",
                streamKey: "dadf",
                speaking: false,
                muted: false,
                loading: false
            },
            'ss': {
                name: 'Sara',
                uuid: "e3575c64-57a7-4ac9-968b-da36f205167b",
                streamKey: "ss",
                speaking: true,
                muted: false,
                loading: false
            },
            'aa': {
                name: 'Thrifting',
                uuid: "1537e30ca66042ce9a7d92d3a2d6704f",
                streamKey: "aa",
                speaking: false,
                muted: false,
                loading: false
            },
        }, // set of peers, mapped by stream key, {name, uuid, speaking, muted, loading}
    },

    build: {
        build: 2002,
        compiler: 'Mats',
        isProd: true
    },

    // click lock
    clickLock: true,

    // view states
    isLoading: true,
    isBlocked: false,
    loadingState: 'Preparing to load OpenAudioMc',
    fixedFooter: null,


    translationBanner: null, // null or {detectedAs: 'en', toEn: 'to en', keep: 'keep', reset: function() {}}
    langFile: null, // current lang file
    lang: {} // gets loaded from the lang file, changes cause a full UI re-render
};

export function shouldSettingSave(name) {
    for (let i = 0; i < UNSAVED_SETTINGS.length; i++) {
        let s = UNSAVED_SETTINGS[i];
        if (s.toLowerCase() === name.toLowerCase()) return false;
    }
    return true;
}

export function setGlobalState(stateUpdates) {
    store.dispatch({ type: 'SET_STATE', stateUpdates });

    // save to cookies
    if (stateUpdates.hasOwnProperty("settings")) {
        const settings = stateUpdates.settings;
        for (let key in settings) {
            if (settings.hasOwnProperty(key) && shouldSettingSave(key)) {
                Cookies.set("setting_" + key, settings[key], { expires: 365 });
            }
        }
    }
}

export function getGlobalState() {
    return store.getState();
}

// hacky, but easy reducer
function appReducer(state = initialState, action) {
    switch (action.type) {
        case 'SET_STATE':
            return mergeObjects(state, action.stateUpdates);
        case 'SET_LANG_MESSAGE':
            if (action.payload.key === undefined || action.payload.value === undefined) {
                console.error("Invalid lang message");
                return state;
            }
            return {
                ...state,
                lang: {
                    ...state.lang,
                    [action.payload.key]: action.payload.value
                }
            };
        default:
            return state;
    }
}

function mergeObjects(obj1, obj2) {
    // remove null values
    for (let key in obj1) {
        if (obj1[key] === null) {
            delete obj1[key];
        }
    }

    return {...{
        ...obj1,
        ...obj2,
        ...Object.keys(obj1).reduce((acc, key) => {
            if (obj2[key] && typeof obj1[key] === 'object' && typeof obj2[key] === 'object') {
                acc[key] = mergeObjects(obj1[key], obj2[key]);
            }
            return acc;
        }, {}),
    }};
}

// toggle debug mode when the D key is pressed
document.addEventListener('keydown', function (e) {
    if (e.key === "d") {
        setGlobalState({ debug: !getGlobalState().debug });
    }
});

export const store = createStore(appReducer, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());