(function() {
    "use strict";
    angular.module("ui.tree", []).constant("treeConfig", {
        treeClass: "tsd-doc-tree-wrap",
        emptyTreeClass: "tsd-doc-tree-empty",
        hiddenClass: "tsd-doc-tree-hidden",
        nodesClass: "tsd-doc-tree",
        nodeClass: "tsd-doc-tree-node",
        handleClass: "tsd-doc-tree-node-drag-handle",
        placeHolderClass: "tsd-doc-tree-node-placeholder",
        dragClass: "tsd-doc-tree-node-clone",
        dragThreshold: 3,
        levelThreshold: 30
    })
})();
(function() {
    "use strict";
    angular.module("ui.tree").factory("$uiTreeHelper", ["$document", "$window", function(e, t) {
        return {
            nodrag: function(e) {
                return typeof e.attr("data-nodrag") != "undefined"
            },
            eventObj: function(e) {
                var t = e;
                if (e.targetTouches !== undefined) {
                    t = e.targetTouches.item(0)
                } else if (e.originalEvent !== undefined && e.originalEvent.targetTouches !== undefined) {
                    t = e.originalEvent.targetTouches.item(0)
                }
                return t
            },
            dragInfo: function(e) {
                return {
                    source: e,
                    sourceInfo: {
                        nodeScope: e,
                        index: e.index(),
                        nodesScope: e.$parentNodesScope
                    },
                    index: e.index(),
                    siblings: e.siblings().slice(0),
                    parent: e.$parentNodesScope,
                    moveTo: function(e, t, n) {
                        this.parent = e;
                        this.siblings = t.slice(0);
                        var r = this.siblings.indexOf(this.source);
                        if (r > -1) {
                            this.siblings.splice(r, 1);
                            if (this.source.index() < n) {
                                n--
                            }
                        }
                        this.siblings.splice(n, 0, this.source);
                        this.index = n
                    },
                    parentNode: function() {
                        return this.parent.$nodeScope
                    },
                    prev: function() {
                        if (this.index > 0) {
                            return this.siblings[this.index - 1]
                        }
                        return null
                    },
                    next: function() {
                        if (this.index < this.siblings.length - 1) {
                            return this.siblings[this.index + 1]
                        }
                        return null
                    },
                    isDirty: function() {
                        return this.source.$parentNodesScope != this.parent || this.source.index() != this.index
                    },
                    eventArgs: function(e, t) {
                        return {
                            source: this.sourceInfo,
                            dest: {
                                index: this.index,
                                nodesScope: this.parent
                            },
                            elements: e,
                            pos: t
                        }
                    },
                    apply: function() {
                        var e = this.source.$modelValue
                    }
                }
            },
            height: function(e) {
                return e.prop("scrollHeight")
            },
            width: function(e) {
                return e.prop("scrollWidth")
            },
            offset: function(n) {
                var r = n[0].getBoundingClientRect();
                return {
                    width: n.prop("offsetWidth"),
                    height: n.prop("offsetHeight"),
                    top: r.top + (t.pageYOffset || e[0].body.scrollTop || e[0].documentElement.scrollTop),
                    left: r.left + (t.pageXOffset || e[0].body.scrollLeft || e[0].documentElement.scrollLeft)
                }
            },
            positionStarted: function(e, t) {
                var n = {};
                n.offsetX = e.pageX - this.offset(t).left;
                n.offsetY = e.pageY - this.offset(t).top;
                n.startX = n.lastX = e.pageX;
                n.startY = n.lastY = e.pageY;
                n.nowX = n.nowY = n.distX = n.distY = n.dirAx = 0;
                n.dirX = n.dirY = n.lastDirX = n.lastDirY = n.distAxX = n.distAxY = 0;
                return n
            },
            positionMoved: function(e, t, n) {
                t.lastX = t.nowX;
                t.lastY = t.nowY;
                t.nowX = e.pageX;
                t.nowY = e.pageY;
                t.distX = t.nowX - t.lastX;
                t.distY = t.nowY - t.lastY;
                t.lastDirX = t.dirX;
                t.lastDirY = t.dirY;
                t.dirX = t.distX === 0 ? 0 : t.distX > 0 ? 1 : -1;
                t.dirY = t.distY === 0 ? 0 : t.distY > 0 ? 1 : -1;
                var r = Math.abs(t.distX) > Math.abs(t.distY) ? 1 : 0;
                if (n) {
                    t.dirAx = r;
                    t.moving = true;
                    return
                }
                if (t.dirAx !== r) {
                    t.distAxX = 0;
                    t.distAxY = 0
                } else {
                    t.distAxX += Math.abs(t.distX);
                    if (t.dirX !== 0 && t.dirX !== t.lastDirX) {
                        t.distAxX = 0
                    }
                    t.distAxY += Math.abs(t.distY);
                    if (t.dirY !== 0 && t.dirY !== t.lastDirY) {
                        t.distAxY = 0
                    }
                }
                t.dirAx = r
            }
        }
    }])
})();
(function() {
    "use strict";
    angular.module("ui.tree").controller("TreeController", ["$scope", "$element", "$attrs", "treeConfig", function(e, t, n, r) {
        this.scope = e;
        e.$element = t;
        e.$nodesScope = null;
        e.$type = "uiTree";
        e.$emptyElm = null;
        e.$callbacks = null;
        e.dragEnabled = true;
        e.maxDepth = 0;
        e.dragDelay = 0;
        e.isEmpty = function() {
            return e.$nodesScope && e.$nodesScope.$modelValue && e.$nodesScope.$modelValue.length === 0
        };
        e.place = function(t) {
            e.$nodesScope.$element.append(t);
            e.$emptyElm.remove()
        };
        e.resetEmptyElement = function() {
            if (e.$nodesScope.$modelValue.length === 0) {
                t.append(e.$emptyElm)
            } else {
                e.$emptyElm.remove()
            }
        };
        var i = function(e, t) {
            var n = e.childNodes();
            for (var r = 0; r < n.length; r++) {
                t ? n[r].collapse() : n[r].expand();
                var s = n[r].$childNodesScope;
                if (s) {
                    i(s, t)
                }
            }
        };
        e.collapseAll = function() {
            i(e.$nodesScope, true)
        };
        e.expandAll = function() {
            i(e.$nodesScope, false)
        }
    }])
})();
(function() {
    "use strict";
    angular.module("ui.tree").controller("TreeNodesController", ["$scope", "$element", "$timeout", "treeConfig", function(e, t, n, r) {
        this.scope = e;
        e.$element = t;
        e.$modelValue = null;
        e.$nodeScope = null;
        e.$treeScope = null;
        e.$type = "uiTreeNodes";
        e.$nodesMap = {};
        e.nodrop = false;
        e.maxDepth = 0;
        e.initSubNode = function(t) {
            n(function() {
                e.$nodesMap[t.$modelValue.$$hashKey] = t
            })
        };
        e.destroySubNode = function(t) {
            e.$nodesMap[t.$modelValue.$$hashKey] = null
        };
        e.accept = function(t, n) {
            return e.$treeScope.$callbacks.accept(t, e, n)
        };
        e.isParent = function(t) {
            return t.$parentNodesScope == e
        };
        e.hasChild = function() {
            return e.$modelValue.length > 0
        };
        e.safeApply = function(e) {
            var t = this.$root.$$phase;
            if (t == "$apply" || t == "$digest") {
                if (e && typeof e === "function") {
                    e()
                }
            } else {
                this.$apply(e)
            }
        };
        e.removeNode = function(t) {
            var n = e.$modelValue.indexOf(t.$modelValue);
            if (n > -1) {
                e.safeApply(function() {
                    e.$modelValue.splice(n, 1)[0]
                });
                return t
            }
            return null
        };
        e.insertNode = function(t, n) {
            e.safeApply(function() {
                e.$modelValue.splice(t, 0, n)
            })
        };
        e.childNodes = function() {
            var t = [];
            if (e.$modelValue) {
                for (var n = 0; n < e.$modelValue.length; n++) {
                    t.push(e.$nodesMap[e.$modelValue[n].$$hashKey])
                }
            }
            return t
        };
        e.depth = function() {
            if (e.$nodeScope) {
                return e.$nodeScope.depth()
            }
            return 0
        };
        e.outOfDepth = function(t) {
            var n = e.maxDepth || e.$treeScope.maxDepth;
            if (n > 0) {
                return e.depth() + t.maxSubDepth() + 1 > n
            }
            return false
        }
    }])
})();
(function() {
    "use strict";
    angular.module("ui.tree").controller("TreeNodeController", ["$scope", "$element", "$attrs", "treeConfig", function(e, t, n, r) {
        this.scope = e;
        e.$element = t;
        e.$modelValue = null;
        e.$parentNodeScope = null;
        e.$childNodesScope = null;
        e.$parentNodesScope = null;
        e.$treeScope = null;
        e.$handleScope = null;
        e.$type = "uiTreeNode";
        e.$$apply = false;
        e.collapsed = false;
        e.init = function(n) {
            var r = n[0];
            e.$treeScope = n[1] ? n[1].scope : null;
            e.$parentNodeScope = r.scope.$nodeScope;
            e.$modelValue = r.scope.$modelValue[e.$index];
            e.$parentNodesScope = r.scope;
            r.scope.initSubNode(e);
            t.on("$destroy", function() {
                r.scope.destroySubNode(e)
            })
        };
        e.index = function() {
            return e.$parentNodesScope.$modelValue.indexOf(e.$modelValue)
        };
        e.dragEnabled = function() {
            return !(e.$treeScope && !e.$treeScope.dragEnabled)
        };
        e.isSibling = function(t) {
            return e.$parentNodesScope == t.$parentNodesScope
        };
        e.isChild = function(t) {
            var n = e.childNodes();
            return n && n.indexOf(t) > -1
        };
        e.prev = function() {
            var t = e.index();
            if (t > 0) {
                return e.siblings()[t - 1]
            }
            return null
        };
        e.siblings = function() {
            return e.$parentNodesScope.childNodes()
        };
        e.childNodesCount = function() {
            return e.childNodes() ? e.childNodes().length : 0
        };
        e.hasChild = function() {
            return e.childNodesCount() > 0
        };
        e.childNodes = function() {
            return e.$childNodesScope && e.$childNodesScope.$modelValue ? e.$childNodesScope.childNodes() : null
        };
        e.accept = function(t, n) {
            return e.$childNodesScope && e.$childNodesScope.accept(t, n)
        };
        e.remove = function() {
            return e.$parentNodesScope.removeNode(e)
        };
        e.toggle = function() {
            e.collapsed = !e.collapsed
        };
        e.collapse = function() {
            e.collapsed = true
        };
        e.expand = function() {
            e.collapsed = false
        };
        e.depth = function() {
            var t = e.$parentNodeScope;
            if (t) {
                return t.depth() + 1
            }
            return 1
        };
        var i = 0;
        var s = function(e) {
            var t = 0;
            var n = e.childNodes();
            for (var r = 0; r < n.length; r++) {
                var o = n[r].$childNodesScope;
                if (o) {
                    t = 1;
                    s(o)
                }
            }
            i += t
        };
        e.maxSubDepth = function() {
            i = 0;
            if (e.$childNodesScope) {
                s(e.$childNodesScope)
            }
            return i
        }
    }])
})();
(function() {
    "use strict";
    angular.module("ui.tree").controller("TreeHandleController", ["$scope", "$element", "$attrs", "treeConfig", function(e, t, n, r) {
        this.scope = e;
        e.$element = t;
        e.$nodeScope = null;
        e.$type = "uiTreeHandle"
    }])
})();

(function() {
    "use strict";
    angular.module("ui.tree").directive("uiTree", ["treeConfig", "$window", function(e, t) {
        return {
            restrict: "A",
            scope: true,
            controller: "TreeController",
            link: function(n, r, i) {
                var s = {
                    accept: null
                };
                var o = {};
                angular.extend(o, e);
                if (o.treeClass) {
                    r.addClass(o.treeClass)
                }
                n.$emptyElm = angular.element(t.document.createElement("div"));
                if (o.emptyTreeClass) {
                    n.$emptyElm.addClass(o.emptyTreeClass)
                }
                n.$watch("$nodesScope.$modelValue.length", function() {
                    if (n.$nodesScope && n.$nodesScope.$modelValue) {
                        n.resetEmptyElement()
                    }
                }, true);
                n.$watch(function() {
                    return n.$eval(i.dragEnabled)
                }, function(e) {
                    if (typeof e == "boolean") {
                        n.dragEnabled = e
                    }
                }, true);
                n.$watch(function() {
                    return n.$eval(i.maxDepth)
                }, function(e) {
                    if (typeof e == "number") {
                        n.maxDepth = e
                    }
                }, true);
                n.$watch(function() {
                    return n.$eval(i.dragDelay)
                }, function(e) {
                    if (typeof e == "number") {
                        n.dragDelay = e
                    }
                }, true);
                s.accept = function(e, t, n) {
                    if (t.nodrop || t.outOfDepth(e)) {
                        return false
                    }
                    return true
                };
                s.dropped = function(e) {};
                s.dragStart = function(e) {};
                s.dragMove = function(e) {};
                s.dragStop = function(e) {};
                n.$watch(i.uiTree, function(e, t) {
                    angular.forEach(e, function(e, t) {
                        if (s[t]) {
                            if (typeof e === "function") {
                                s[t] = e
                            }
                        }
                    });
                    n.$callbacks = s
                }, true)
            }
        }
    }])
})();
(function() {
    "use strict";
    angular.module("ui.tree").directive("uiTreeNodes", ["treeConfig", "$window", function(e) {
        return {
            require: ["ngModel", "?^uiTreeNode", "^uiTree"],
            restrict: "A",
            scope: true,
            controller: "TreeNodesController",
            link: function(t, n, r, i) {
                var s = {};
                angular.extend(s, e);
                if (s.nodesClass) {
                    n.addClass(s.nodesClass)
                }
                var o = i[0];
                var u = i[1];
                var a = i[2];
                if (u) {
                    u.scope.$childNodesScope = t;
                    t.$nodeScope = u.scope
                } else {
                    a.scope.$nodesScope = t
                }
                t.$treeScope = a.scope;
                if (o) {
                    o.$render = function() {
                        t.$modelValue = o.$modelValue
                    }
                }
                t.$watch(function() {
                    return t.$eval(r.maxDepth)
                }, function(e) {
                    if (typeof e == "number") {
                        t.maxDepth = e
                    }
                }, true);
                t.$watch(function() {
                    return r.nodrop
                }, function(e) {
                    if (typeof e != "undefined") {
                        t.nodrop = true
                    }
                }, true)
            }
        }
    }])
})();
(function() {
    "use strict";
    angular.module("ui.tree").directive("uiTreeNode", ["treeConfig", "$uiTreeHelper", "$window", "$document", "$timeout", function(e, t, n, r, i) {
        return {
            require: ["^uiTreeNodes", "^uiTree"],
            restrict: "A",
            controller: "TreeNodeController",
            link: function(s, o, u, a) {
                var f = {};
                angular.extend(f, e);
                if (f.nodeClass) {
                    o.addClass(f.nodeClass)
                }
                s.init(a);
                var l = "ontouchstart" in window;
                var c, h, p, d;
                var v, m, g;
                var y = null;
                var b;
                var w = null;
                var E = function(e) {
                    if (!l && (e.button == 2 || e.which == 3)) {
                        return
                    }
                    if (e.uiTreeDragging || e.originalEvent && e.originalEvent.uiTreeDragging) {
                        return
                    }
                    var i = angular.element(e.target);
                    var u = i.scope();
                    if (!u || !u.$type) {
                        return
                    }
                    if (u.$type != "uiTreeNode" && u.$type != "uiTreeHandle") {
                        return
                    }
                    if (u.$type == "uiTreeNode" && u.$handleScope) {
                        return
                    }
                    while (i && i[0] && i[0] != o) {
                        if (t.nodrag(i)) {
                            return
                        }
                        i = i.parent()
                    }
                    e.uiTreeDragging = true;
                    if (e.originalEvent) {
                        e.originalEvent.uiTreeDragging = true
                    }
                    e.preventDefault();
                    var a = t.eventObj(e);
                    h = true;
                    p = t.dragInfo(s);
                    var c = s.$element.prop("tagName");
                    if (c.toLowerCase() === "tr") {
                        v = angular.element(n.document.createElement(c));
                        var y = angular.element(n.document.createElement("td")).addClass(f.placeHolderClass);
                        v.append(y)
                    } else {
                        v = angular.element(n.document.createElement(c)).addClass(f.placeHolderClass)
                    }
                    m = angular.element(n.document.createElement(c));
                    if (f.hiddenClass) {
                        m.addClass(f.hiddenClass)
                    }
                    d = t.positionStarted(a, s.$element);
                    v.css("height", t.height(s.$element) + "px");
                    g = angular.element(n.document.createElement(s.$parentNodesScope.$element.prop("tagName"))).addClass(s.$parentNodesScope.$element.attr("class")).addClass(f.dragClass);
                    g.css("width", t.width(s.$element) + "px");
                    g.css("z-index", 9999);
                    s.$element.after(v);
                    s.$element.after(m);
                    g.append(s.$element);
                    r.find("body").append(g);
                    g.css({
                        left: a.pageX - d.offsetX + "px",
                        top: a.pageY - d.offsetY + "px"
                    });
                    b = {
                        placeholder: v,
                        dragging: g
                    };
                    s.$apply(function() {
                        s.$callbacks.dragStart(p.eventArgs(b, d))
                    });
                    angular.element(r).bind("touchend", C);
                    angular.element(r).bind("touchcancel", C);
                    angular.element(r).bind("touchmove", N);
                    angular.element(r).bind("mouseup", C);
                    angular.element(r).bind("mousemove", N);
                    angular.element(n.document.body).bind("mouseleave", k)
                };
                var S = function(e) {
                    var r = t.eventObj(e);
                    var i;
                    if (g) {
                        e.preventDefault();
                        g.css({
                            left: r.pageX - d.offsetX + "px",
                            top: r.pageY - d.offsetY + "px"
                        });
                        t.positionMoved(e, d, h);
                        if (h) {
                            h = false;
                            return
                        }
                        if (d.dirAx && d.distAxX >= f.levelThreshold) {
                            d.distAxX = 0;
                            if (d.distX > 0) {
                                i = p.prev();
                                if (i && !i.collapsed && i.accept(s, i.childNodesCount())) {
                                    i.$childNodesScope.$element.append(v);
                                    p.moveTo(i.$childNodesScope, i.childNodes(), i.childNodesCount())
                                }
                            }
                            if (d.distX < 0) {
                                var o = p.next();
                                if (!o) {
                                    var u = p.parentNode();
                                    if (u && u.$parentNodesScope.accept(s, u.index() + 1)) {
                                        u.$element.after(v);
                                        p.moveTo(u.$parentNodesScope, u.siblings(), u.index() + 1)
                                    }
                                }
                            }
                        }
                        var a = t.offset(g).left - t.offset(v).left >= f.threshold;
                        var l = r.pageX - n.document.body.scrollLeft;
                        var c = r.pageY - (window.pageYOffset || n.document.documentElement.scrollTop);
                        if (angular.isFunction(g.hide)) {
                            g.hide()
                        }
                        n.document.elementFromPoint(l, c);
                        var m = angular.element(n.document.elementFromPoint(l, c));
                        if (angular.isFunction(g.show)) {
                            g.show()
                        }
                        if (!d.dirAx) {
                            var w, E;
                            E = m.scope();
                            var S = false;
                            if (E.$type == "uiTree" && E.dragEnabled) {
                                S = E.isEmpty()
                            }
                            if (E.$type == "uiTreeHandle") {
                                E = E.$nodeScope
                            }
                            if (E.$type != "uiTreeNode" && !S) {
                                return
                            }
                            if (y && v.parent()[0] != y.$element[0]) {
                                y.resetEmptyElement();
                                y = null
                            }
                            if (S) {
                                y = E;
                                if (E.$nodesScope.accept(s, 0)) {
                                    E.place(v);
                                    p.moveTo(E.$nodesScope, E.$nodesScope.childNodes(), 0)
                                }
                            } else if (E.dragEnabled()) {
                                m = E.$element;
                                var x = t.offset(m);
                                w = r.pageY < x.top + t.height(m) / 2;
                                if (E.$parentNodesScope.accept(s, E.index())) {
                                    if (w) {
                                        m[0].parentNode.insertBefore(v[0], m[0]);
                                        p.moveTo(E.$parentNodesScope, E.siblings(), E.index())
                                    } else {
                                        m.after(v);
                                        p.moveTo(E.$parentNodesScope, E.siblings(), E.index() + 1)
                                    }
                                } else if (!w && E.accept(s, E.childNodesCount())) {
                                    E.$childNodesScope.$element.append(v);
                                    p.moveTo(E.$childNodesScope, E.childNodes(), E.childNodesCount())
                                }
                            }
                        }
                        s.$apply(function() {
                            s.$callbacks.dragMove(p.eventArgs(b, d))
                        })
                    }
                };
                var x = function(e) {
                    e.preventDefault();
                    if (g) {
                        m.replaceWith(s.$element);
                        v.remove();
                        g.remove();
                        g = null;
                        if (s.$$apply) {
                            s.$treeScope.$apply(function() {
                                s.$callbacks.dropped(p.eventArgs(b, d))
                            })
                        } else {
                            L()
                        }
                        s.$treeScope.$apply(function() {
                            s.$callbacks.dragStop(p.eventArgs(b, d))
                        });
                        s.$$apply = false;
                        p = null
                    }
                    angular.element(r).unbind("touchend", C);
                    angular.element(r).unbind("touchcancel", C);
                    angular.element(r).unbind("touchmove", N);
                    angular.element(r).unbind("mouseup", C);
                    angular.element(r).unbind("mousemove", N);
                    angular.element(n.document.body).unbind("mouseleave", k)
                };
                var T = function(e) {
                    if (s.dragEnabled()) {
                        E(e)
                    }
                };
                var N = function(e) {
                    S(e)
                };
                var C = function(e) {
                    s.$$apply = true;
                    x(e)
                };
                var k = function(e) {
                    x(e)
                };
                var L = function() {
                    o.bind("touchstart", T);
                    o.bind("mousedown", function(e) {
                        w = i(function() {
                            T(e)
                        }, s.dragDelay);
                        e.preventDefault()
                    });
                    o.bind("mouseup", function() {
                        i.cancel(w)
                    })
                };
                L();
                angular.element(n.document.body).bind("keydown", function(e) {
                    if (e.keyCode == 27) {
                        s.$$apply = false;
                        x(e)
                    }
                })
            }
        }
    }])
})();
(function() {
    "use strict";
    angular.module("ui.tree").directive("uiTreeHandle", ["treeConfig", "$window", function(e) {
        return {
            require: "^uiTreeNode",
            restrict: "A",
            scope: true,
            controller: "TreeHandleController",
            link: function(t, n, r, i) {
                var s = {};
                angular.extend(s, e);
                if (s.handleClass) {
                    n.addClass(s.handleClass)
                }
                if (t != i.scope) {
                    t.$nodeScope = i.scope;
                    i.scope.$handleScope = t
                }
            }
        }
    }])
})();