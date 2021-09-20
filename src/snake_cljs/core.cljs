(ns snake-cljs.core
  (:require
    [reagent.core :as reagent :refer [atom]]))

;; define your app data so that it doesn't get over-written on reload

(def w-max 20)
(def h-max 20)
(def tam-ret 15)

(defonce app-state (atom {:text "Hello world!"}))

(defn retangulo [x y cor]
  [:rect
   {:width tam-ret
    :height tam-ret
    :fill cor
    :x x
    :y y}])

(defn ->retangulo [x y cor]
  (retangulo (* x tam-ret) (* y tam-ret) cor))

(defn fundo []
  (into []
        (for [x (range 0 30)
              y (range 0 30)]
          (->retangulo x y "gray"))))

(defn snake-world []
  [:div
   (into
     [:svg
      (let [w 300 h 300]
        {:view-box (str "0 0 " w " " h)
         :width w
         :height h})]
     (fundo))])

(defn start []
  (reagent/render-component [snake-world]
                            (. js/document (getElementById "app"))))

(defn ^:export init []
  ;; init is called ONCE when the page loads
  ;; this is called in the index.html and must be exported
  ;; so it is available even in :advanced release builds
  (start))

(defn stop []
  ;; stop is called before any code is reloaded
  ;; this is controlled by :before-load in the config
  (js/console.log "stop"))
