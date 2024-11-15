interface Cache {
    set(key: string, value: string): void;
    get(key: string): string | null;
    setJSON(key: string, jsonValue: any): void;
    getJSON(key: string): any;
    remove(key: string): void;
  }
  
  const sessionCache: Cache = {
    set(key: string, value: string): void {
      if (!sessionStorage) {
        return;
      }
      if (key != null && value != null) {
        sessionStorage.setItem(key, value);
      }
    },
    get(key: string): string | null {
      if (!sessionStorage) {
        return null;
      }
      if (key == null) {
        return null;
      }
      return sessionStorage.getItem(key);
    },
    setJSON(key: string, jsonValue: any): void {
      if (jsonValue != null) {
        this.set(key, JSON.stringify(jsonValue));
      }
    },
    getJSON(key: string): any {
      const value = this.get(key);
      if (value != null) {
        return JSON.parse(value);
      }
      return null;
    },
    remove(key: string): void {
      sessionStorage.removeItem(key);
    }
  };
  
  const localCache: Cache = {
    set(key: string, value: string): void {
      if (!localStorage) {
        return;
      }
      if (key != null && value != null) {
        localStorage.setItem(key, value);
      }
    },
    get(key: string): string | null {
      if (!localStorage) {
        return null;
      }
      if (key == null) {
        return null;
      }
      return localStorage.getItem(key);
    },
    setJSON(key: string, jsonValue: any): void {
      if (jsonValue != null) {
        this.set(key, JSON.stringify(jsonValue));
      }
    },
    getJSON(key: string): any {
      const value = this.get(key);
      if (value != null) {
        return JSON.parse(value);
      }
      return null;
    },
    remove(key: string): void {
      localStorage.removeItem(key);
    }
  };
  
  export default {
    /**
     * 会话级缓存
     */
    session: sessionCache,
    /**
     * 本地缓存
     */
    local: localCache
  };
  