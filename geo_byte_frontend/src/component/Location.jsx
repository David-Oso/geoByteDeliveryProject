import React from 'react';
import { Link } from 'react-router-dom';

function Location() {
  return (
    <div>
        <div className="flex h-screen justify-center items-center">
            <Link to="/locations-table">
                <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                    View Locations
                </button>
            </Link>
        </div>
    </div>
  );
};

export default Location;
